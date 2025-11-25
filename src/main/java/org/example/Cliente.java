package org.example;

import java.net.*;
import java.io.*;

public class Cliente {

    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO  = 4000;

    // Porta para Node.js enviar a senha
    public static final int PORTA_NODE = 1010;

    public static void main(String[] args) throws Exception {

        ServerSocket portaParaNode = new ServerSocket(PORTA_NODE);
        System.out.println("Aguardando Node.js na porta " + PORTA_NODE + "...");

        while (true) {

            Socket conexaoNode = portaParaNode.accept();
            System.out.println("Node.js conectado!");

            BufferedReader inNode = new BufferedReader(
                    new InputStreamReader(conexaoNode.getInputStream())
            );
            PrintWriter outNode = new PrintWriter(
                    conexaoNode.getOutputStream(), true
            );

            // recebe a senha do Node
            String senhaRecebida = inNode.readLine();
            System.out.println("Recebido: " + senhaRecebida);

            if (senhaRecebida.startsWith("GET") || senhaRecebida.startsWith("POST")) {
                System.out.println("Ignorando requisição HTTP...");
                conexaoNode.close();
                continue;
            }

            boolean senhaValida = new Senha(senhaRecebida).isSenhaValida();

            // devolve o resultado (true/false)
            outNode.println(senhaValida ? "true" : "false");

            // Se a senha for inválida, encerra e espera outro Node conectar
            if (!senhaValida) {
                conexaoNode.close();
                continue;
            }


            Socket conexaoServidor = new Socket(HOST_PADRAO, PORTA_PADRAO);
            ObjectOutputStream transmissor =
                    new ObjectOutputStream(conexaoServidor.getOutputStream());
            ObjectInputStream receptor =
                    new ObjectInputStream(conexaoServidor.getInputStream());

            Parceiro servidor = new Parceiro(conexaoServidor, receptor, transmissor);

            // envia pedido para sair
            servidor.receba(new PedidoPraSair());

            // fecha tudo
            conexaoServidor.close();
            conexaoNode.close();

            break; // encerra o cliente
        }

        System.out.println("Cliente finalizado.");
    }
}