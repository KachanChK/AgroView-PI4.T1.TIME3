package org.example.handlers;

import com.agroview.db.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PlantioHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Só aceita POST
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            String response = "Método não permitido. Use POST.";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            return;
        }

        // Lê o corpo da requisição (JSON)
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }




        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("plantios");

            // Cria documento a partir do JSON recebido
            Document plantio = Document.parse(body.toString());
            collection.insertOne(plantio);

            String response = "✅ Plantio cadastrado com sucesso!";
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
            String response = "❌ Erro ao cadastrar plantio: " + e.getMessage();
            exchange.sendResponseHeaders(500, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
