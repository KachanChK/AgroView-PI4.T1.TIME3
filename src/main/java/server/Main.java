package server;

public class Main {
    public static void main(String[] args) {
        HttpServerAgroView server = new HttpServerAgroView(8080);
        server.start();
    }
}
