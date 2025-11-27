package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

public class TesteServidorFalhaAntesValidar {

    private final int PORTA = 5055;

    // Libera porta igual aos outros testes
    private void liberarPorta(int porta) {
        try {
            ServerSocket ss = new ServerSocket(porta);
            ss.close();
        } catch (Exception ignored) {}
    }


    private void iniciarServidorComFalhaAntes() {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(PORTA)) {

                System.out.println("[SERVIDOR] Aguardando conexão...");
                Socket cliente = server.accept();
                System.out.println("[SERVIDOR] Cliente conectou, simulando falha...");

                // FALHA proposital antes da validação
                throw new RuntimeException("Falha interna antes da validação!");

            } catch (Exception e) {
                System.out.println("[SERVIDOR] Servidor caiu propositalmente: " + e.getMessage());
            }
        }).start();
    }


    @Test
    public void testeServidorFalhaAntesValidacao() throws Exception {

        liberarPorta(PORTA);

        iniciarServidorComFalhaAntes();
        Thread.sleep(200); // tempo para servidor iniciar

        System.out.println("[CLIENTE] Tentando conectar...");

        try (Socket cliente = new Socket("localhost", PORTA)) {

            assertThrows(IOException.class, () -> {
                OutputStream out = cliente.getOutputStream();
                InputStream in = cliente.getInputStream();

                // Tenta enviar senha — servidor já caiu
                out.write("1234\n".getBytes());
                out.flush();

                // Tenta ler resposta — servidor não responde
                int r = in.read();   // aqui deve explodir IOException
                System.out.println("[CLIENTE] Recebeu: " + r);
            });

        } catch (IOException e) {
            System.out.println("[CLIENTE] Erro esperado: " + e.getMessage());
        }
    }
}