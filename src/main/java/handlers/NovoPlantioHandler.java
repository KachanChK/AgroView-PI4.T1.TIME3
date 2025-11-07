package handlers;

import db.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bson.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/*
Rota POST
URL: http://localhost:8080/novoPlantio
Header:
key: Content-Type
Value: application/json

Body:
{
  "plantio": "Soja",
  "area": 120,
  "quantidadePlantio": 60,
  "gastoEstimado": 140,
  "producaoEsperada": 3000,
  "observacoes": "vazio",
  "status": "Plantado",
  "dataPlantio": "2025-11-04"
}
*/

public class NovoPlantioHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String response = "Método não permitido";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            return;
        }

        // Lê o corpo da requisição (JSON)
        InputStream inputStream = exchange.getRequestBody();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] tmp = new byte[1024];
        int bytesRead;
    
        while ((bytesRead = inputStream.read(tmp)) != -1) {
            buffer.write(tmp, 0, bytesRead);
        }

        String json = new String(buffer.toByteArray(), StandardCharsets.UTF_8);

        // Conecta ao MongoDB
        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> collection = db.getCollection("plantios");

        // Insere o documento recebido
        Document plantio = Document.parse(json);
        collection.insertOne(plantio);

        String response = "Novo plantio registrado com sucesso!";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
