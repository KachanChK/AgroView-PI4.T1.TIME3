package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread {

    private Parceiro usuario;
    private final Socket conexao;
    private final ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception {

        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (usuarios == null)
            throw new Exception("Usuarios ausentes");

        this.conexao = conexao;
        this.usuarios = usuarios;
    }

    @Override
    public void run() {

        try {

            BufferedReader receptor = new BufferedReader(
                    new InputStreamReader(this.conexao.getInputStream())
            );

            PrintWriter transmissor = new PrintWriter(
                    this.conexao.getOutputStream(), true
            );

            this.usuario = new Parceiro(this.conexao, receptor, transmissor);

            synchronized (usuarios) {
                usuarios.add(usuario);
            }

            String senhaRecebida = usuario.envie(); // BLOQUEIA esperando senha

            boolean valida;

            try {
                Senha s = new Senha(senhaRecebida.trim());
                valida = s.isSenhaValida();
            }
            catch (Exception e) {
                valida = false;
            }

            usuario.receba(valida ? "true" : "false");

        }
        catch (Exception e) {
            // erro já tratado abaixo
        }
        finally {
            // garante que sempre remove
            synchronized (usuarios) {
                usuarios.remove(usuario);
            }

            try {
                if (usuario != null)
                    usuario.adeus();
            } catch (Exception ignored) {}

            try { conexao.close(); } catch (Exception ignored) {}
        }
    }
}