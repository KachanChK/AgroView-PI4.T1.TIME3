package org.example;
import java.net.*;
import java.io.*;

public class Cliente {
    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 4000;

    public static void main(String[] args) throws Exception {

        if (args.length > 2) {
            System.err.println("Uso esperado: java Cliente [HOST [PORTA]]\n");
            return;
        }

        Socket conexao = null;
        try {
            String host = Cliente.HOST_PADRAO;
            int porta = Cliente.PORTA_PADRAO;

            if (args.length > 0) {
                host = args[0];
            }

            if (args.length == 2) {
                porta = Integer.parseInt(args[1]);
            }

            conexao = new Socket(host, porta);
        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta");
        }

        ObjectOutputStream transmissor = null;
        try {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta");
            return;
        }

        ObjectInputStream receptor = null;
        try {
            receptor = new ObjectInputStream(conexao.getInputStream());
        } catch (Exception erro) {
            System.err.println("Indique o servidor e a porta");
            return;
        }

        Parceiro servidor = null;
        try{
            servidor = new Parceiro(conexao, receptor, transmissor);
        } catch (Exception erro){
            System.err.println("Indique o servidor e a porta");
            return;
        }

        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento  = null;
        try{
            tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor);
        } catch (Exception erro){}

        tratadoraDeComunicadoDeDesligamento.start();

        char opcao = ' ';

        do {
            System.out.println("1- Logar\n2- Registrar\n0- Sair\n");
            System.out.println("Digite opcao que deseja: ");

            try{
                opcao = Teclado.getUmChar();
            } catch (Exception erro){
                System.err.println("Opcao invalida! \n");
                continue;
            }

            if ("120".indexOf( opcao ) == -1){
                System.err.println("Opcao invalida! \n");
                continue;
            }




            try{
                servidor.receba (new PedidoPraSair());
            } catch (Exception erro) {}

            System.out.println("Obrigado por usar este programa!");
            System.exit(0);
        }

        while(opcao != '0');
        try{
            servidor.receba (new PedidoPraSair());
        } catch (Exception erro) {}


    }
}
