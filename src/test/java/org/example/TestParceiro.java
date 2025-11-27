package org.example;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestParceiro {

    private PipedOutputStream clientOut;
    private PipedInputStream  serverIn;

    private PipedOutputStream serverOut;
    private PipedInputStream  clientIn;

    private ObjectOutputStream transmissor;     // Parceiro -> Servidor
    private ObjectInputStream receptor;         // Servidor -> Parceiro

    private ObjectOutputStream serverWriter;    // Servidor -> Parceiro
    private ObjectInputStream serverReader;     // Parceiro -> Servidor

    private Parceiro parceiro;

    @BeforeEach
    public void setUp() throws Exception {

        // Pipes cliente → servidor
        clientOut = new PipedOutputStream();
        serverIn  = new PipedInputStream(clientOut);

        // Pipes servidor → cliente
        serverOut = new PipedOutputStream();
        clientIn  = new PipedInputStream(serverOut);

        serverWriter = new ObjectOutputStream(serverOut); // servidor → parceiro
        serverWriter.flush();

        transmissor = new ObjectOutputStream(clientOut);  // parceiro → servidor
        transmissor.flush();

        receptor = new ObjectInputStream(clientIn);       // parceiro recebe do servidor
        serverReader = new ObjectInputStream(serverIn);   // servidor recebe do parceiro

        parceiro = new Parceiro(
                new FakeSocket(),
                receptor,
                transmissor
        );
    }


    @Test
    public void testReceba() throws Exception {
        Msg msg = new Msg("Enviado");

        parceiro.receba(msg);
        transmissor.flush();

        Msg recebido = (Msg) serverReader.readObject();

        assertEquals("Enviado", recebido.texto);
    }


    @Test
    public void testEnvie() throws Exception {
        serverWriter.writeObject(new Msg("OK"));
        serverWriter.flush();

        Msg recebido = (Msg) parceiro.envie();

        assertEquals("OK", recebido.texto);
    }


    @Test
    public void testEspieMantemBuffer() throws Exception {
        serverWriter.writeObject(new Msg("Primeiro"));
        serverWriter.flush();

        Msg a = (Msg) parceiro.espie();
        Msg b = (Msg) parceiro.espie();

        // Espie NÃO consome, então os objetos devem ser iguais
        assertSame(a, b);
    }


    @Test
    public void testEspieDepoisEnvie() throws Exception {
        serverWriter.writeObject(new Msg("Teste"));
        serverWriter.flush();

        Msg a = (Msg) parceiro.espie();
        Msg b = (Msg) parceiro.envie();

        // espie lê mas NÃO consome, então envie devolve o mesmo objeto
        assertSame(a, b);
    }


    @Test
    public void testAdeus() throws Exception {
        parceiro.adeus();

        assertThrows(Exception.class, () -> transmissor.writeObject(new Msg("x")));
    }


    // CLASSES AUXILIARES

    public static class FakeSocket extends java.net.Socket {
        @Override public synchronized void close() {}
    }

    public static class Msg extends Comunicado implements Serializable {
        public String texto;
        public Msg(String t) { texto = t; }
    }
}