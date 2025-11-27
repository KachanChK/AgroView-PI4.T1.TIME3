package org.example;

import java.util.*;


public class Servidor {

    public static final String PORTA_PADRAO = "4000";

    public static void main(String[] args) {

        if (args.length > 1) {
            System.err.println("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        String porta = Servidor.PORTA_PADRAO;

        if (args.length == 1) {
            porta = args[0];
        }

        ArrayList <Parceiro> usuarios = new ArrayList <Parceiro>();

        AceitadoraDeConexao aceitadoraDeConexao = null;

        try{
            aceitadoraDeConexao = new AceitadoraDeConexao (porta, usuarios);
            aceitadoraDeConexao.start();
        } catch (Exception erro){
            System.err.println("Escolha uma porta apropriada");
            return;
        }

        for(;;){
            System.out.println("O servidor esta ativo! Para desliga-lo use o comando \"desativar\"\n");
            System.out.print("> ");

            String comando = null;
            try{
                comando = Teclado.getUmString();
            }catch (Exception erro){}

            if (comando.toLowerCase().equals("desativar")){

                synchronized (usuarios) {
                    ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

                    for (Parceiro usuario : usuarios){
                        try{
                            usuario.receba (comunicadoDeDesligamento);
                            usuario.adeus();
                        } catch (Exception erro){}
                    }
                }
                System.out.println("O servidor foi desativado!");
                System.exit(0);
            }
            else{
                System.err.println("Comando Invalido\n");
            }
        }
    }
}
