package org.example;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInterclassesServidor {

    private static final String PORTA = "2025";
    private static AceitadoraDeConexao aceitadora;
    private static ArrayList<Parceiro> usuarios;

    @BeforeAll
    public static void iniciarServidor() throws Exception {
        usuarios = new ArrayList<>();
        aceitadora = new AceitadoraDeConexao(PORTA, usuarios);
        aceitadora.start();
        Thread.sleep(200);
    }

    @AfterAll
    public static void finalizarServidor() {
        aceitadora.interrupt();
    }

    @Test
    @Order(1)
    public void testeNormalSenhaValida() throws Exception {

        Socket cliente = new Socket("localhost", Integer.parseInt(PORTA));

        PrintWriter transmissor = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader receptor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        transmissor.println("Abc1234$");

        String resposta = receptor.readLine();

        assertEquals("true", resposta, "Servidor deveria retornar true para senha válida.");

        cliente.close();
        Thread.sleep(200);

        assertEquals(0, usuarios.size(), "Usuário deveria ser removido após adeus().");
    }

    @Test
    @Order(2)
    public void testeVariacao1SenhaInvalida() throws Exception {

        Socket cliente = new Socket("localhost", Integer.parseInt(PORTA));

        PrintWriter transmissor = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader receptor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        transmissor.println("abc");

        String resposta = receptor.readLine();

        assertEquals("false", resposta, "Servidor deveria retornar false para senha inválida.");

        cliente.close();
        Thread.sleep(200);
        assertEquals(0, usuarios.size());

    }

    @Test
    @Order(3)
    public void testeVariacao2ClienteDesconectaAntes() throws Exception {

        Socket cliente = new Socket("localhost", Integer.parseInt(PORTA));
        cliente.close();

        cliente.close();
        Thread.sleep(200);
        assertEquals(0, usuarios.size());
    }

    private void esperarRemocaoUsuarios() throws Exception {
        int tentativas = 0;

        while (!usuarios.isEmpty() && tentativas < 20) { // tenta por até 2 segundos
            Thread.sleep(100);
            tentativas++;
        }
    }

}
