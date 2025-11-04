package server;

import handlers.NovoPlantioHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerAgroView {
    private final int port;

    public HttpServerAgroView(int port) {
        this.port = port;
    }

    public void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Registra a rota /novoPlantio
            server.createContext("/novoPlantio", new NovoPlantioHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("🌿 AgroView rodando em http://localhost:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
