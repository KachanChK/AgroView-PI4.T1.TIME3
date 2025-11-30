package org.example;

import java.net.*;
import java.util.*;

public class AceitadoraDeConexao extends Thread {

    private ServerSocket pedido;
    private ArrayList<Parceiro> usuarios;

    public AceitadoraDeConexao(String porta, ArrayList<Parceiro> usuarios) throws Exception {

        if (porta == null) {
            throw new Exception("Porta ausente");
        }

        try {
            this.pedido = new ServerSocket(Integer.parseInt(porta));
        } catch (Exception erro) {
            throw new Exception("Porta inválida");
        }

        if (usuarios == null) {
            throw new Exception("Usuários ausentes");
        }

        this.usuarios = usuarios;
    }

    public void run() {
        for (;;) {

            Socket conexao = null;

            try {
                conexao = this.pedido.accept();
            } catch (Exception erro) {
                continue; // tenta novamente
            }

            SupervisoraDeConexao supervisora;

            try {
                supervisora = new SupervisoraDeConexao(conexao, this.usuarios);
            } catch (Exception erro) {
                continue;
            }

            supervisora.start();
        }
    }
}