package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;
import java.io.*;
import java.net.*;

public class ClienteTesteSenhaInvalida {

    private void matarCliente() {
        try { new Socket("localhost", 1010).close(); }
        catch (Exception ignored) {}
    }

    @Test
    public void TestInvalido() throws Exception{
        Thread fakeServidor = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(4000);
                Socket cliente = ss.accept();

                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

                in.readObject(); // recebe PedidoPraSair

                out.writeObject("true");
                out.flush();

                cliente.close();
                ss.close();

            } catch (Exception e) { e.printStackTrace(); }
        });
        fakeServidor.start();

        Thread clienteThread = new Thread(() -> {
            try { Cliente.main(null); }
            catch (Exception e) { e.printStackTrace(); }
        });
        clienteThread.start();

        Thread.sleep(600);


        Socket fakeNode = new Socket("localhost", 1010);

        PrintWriter out = new PrintWriter(fakeNode.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(fakeNode.getInputStream()));

        out.println("senhaInvalidaExemplo");
        String resposta = in.readLine();

        assertEquals("false", resposta);

        fakeNode.close();
        matarCliente();
    }

}