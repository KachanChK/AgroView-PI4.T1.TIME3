package org.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class Parceiro {

    private Socket conexao;
    private BufferedReader receptor;
    private PrintWriter transmissor;

    private String proximoComunicado = null;

    private Semaphore mutEx = new Semaphore(1, true);

    public Parceiro(Socket conexao, BufferedReader receptor, PrintWriter transmissor) throws Exception {
        if (conexao == null) {
            throw new Exception("Conexao ausente");
        }

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;

    }

    public void receba(String json) throws Exception {
        try {
            this.transmissor.println(json);
        } catch (Exception e) {
            throw new Exception("Erro de transmissao!");
        }
    }

    public String espie() throws Exception {
        try {
            this.mutEx.acquireUninterruptibly();

            if (this.proximoComunicado == null) {
                this.proximoComunicado = this.receptor.readLine();
            }

            this.mutEx.release();
            return this.proximoComunicado;

        } catch (Exception erro) {
            throw new Exception("Erro de recepcao!");
        }
    }

    public String envie() throws Exception {
        try {
            if (this.proximoComunicado == null) {
                this.proximoComunicado = this.receptor.readLine();
            }

            String retorno = this.proximoComunicado;
            this.proximoComunicado = null;

            return retorno;

        } catch (Exception erro) {
            throw new Exception("Erro de recepcao!");
        }
    }

    public void adeus() throws Exception {
        try {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        } catch (Exception erro) {
            throw new Exception("Erro de desconexao!");
        }
    }
}