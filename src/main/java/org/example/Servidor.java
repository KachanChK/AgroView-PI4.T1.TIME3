package org.example;

import java.util.*;

public class Servidor {

    public static final String PORTA_PADRAO = "1010";

    public static void main(String[] args) {

        ArrayList<Parceiro> usuarios = new ArrayList<>();

        try {
            AceitadoraDeConexao aceita =
                    new AceitadoraDeConexao(PORTA_PADRAO, usuarios);

            aceita.start();

            System.out.println("Servidor iniciado na porta " + PORTA_PADRAO + ".");
        }
        catch (Exception erro) {
            System.err.println("Erro ao iniciar servidor.");
            return;
        }


        for(;;) {

            System.out.println("> Servidor ativo. Digite 'desativar' para encerrar.");

            String comando = Teclado.getUmString();

            if (comando.equalsIgnoreCase("desativar")) {

                synchronized (usuarios) {

                    // Fecha todas as conexões ativas
                    for (Parceiro p : usuarios) {
                        try {
                            p.receba("desligar");
                            p.adeus();
                        }
                        catch (Exception e) {}
                    }

                    System.out.println("Servidor encerrado.");
                    System.exit(0);
                }
            }
        }
    }
}