package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.net.*;

public class ClienteTesteInterclasse {

    @Test
    public void TestNormal() throws Exception {

        // Criar servidor fake na porta 4000
        Thread fakeServidor = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(4000);
                Socket cliente = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                // Recebe o PedidoPraSair
                Object obj = in.readObject();
                System.out.println("Servidor fake recebeu: " + obj.getClass().getSimpleName());

                cliente.close();
                serverSocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fakeServidor.start();



        // Iniciar o Cliente numa thread separada
        Thread clienteThread = new Thread(() -> {
            try {
                Cliente.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        clienteThread.start();


        // Esperar o cliente abrir as portas 1010 e 4000
        Thread.sleep(500);


        // Conectar como Node.js no cliente na porta 1010
        Socket fakeNode = new Socket("localhost", 1010);

        PrintWriter out = new PrintWriter(fakeNode.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(fakeNode.getInputStream()));


        //Enviar senha válida
        out.println("Senha123@");

        // Ler resposta
        String resposta = in.readLine();

        assertEquals("true", resposta);

        fakeNode.close();
    }

    @Test
    public void TestSenhaIncorreta() throws Exception {

        // Criar servidor fake na porta 4000
        Thread fakeServidor = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(3000);
                Socket cliente = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                // Recebe o PedidoPraSair
                Object obj = in.readObject();
                System.out.println("Servidor fake recebeu: " + obj.getClass().getSimpleName());

                cliente.close();
                serverSocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        fakeServidor.start();



        // Iniciar o Cliente numa thread separada
        Thread clienteThread = new Thread(() -> {
            try {
                Cliente.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        clienteThread.start();


        // Esperar o cliente abrir as portas 1010 e 4000
        Thread.sleep(500);


        // Conectar como Node.js no cliente na porta 1010
        Socket fakeNode = new Socket("localhost", 1011);

        PrintWriter out = new PrintWriter(fakeNode.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(fakeNode.getInputStream()));


        //Enviar senha válida
        out.println("Senha123@");

        // Ler resposta
        String resposta = in.readLine();

        assertEquals("true", resposta);

        fakeNode.close();
    }
}