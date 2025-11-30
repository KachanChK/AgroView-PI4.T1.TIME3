package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.Socket;

public class ParceiroInfraclasseTest {

    // Socket fake para testar sem rede real
    private static class FakeSocket extends Socket {
        private boolean closed = false;

        @Override
        public synchronized void close() {
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }
    }

    private FakeSocket socket;
    private BufferedReader receptor;
    private PrintWriter transmissor;
    private PipedWriter pipeWriter;

    @BeforeEach
    public void setup() throws Exception {
        socket = new FakeSocket();

        // Simula entrada como se viesse do servidor/cliente
        PipedReader pipedReader = new PipedReader();
        pipeWriter = new PipedWriter(pipedReader);
        receptor = new BufferedReader(pipedReader);

        // Saída simulada
        transmissor = new PrintWriter(new StringWriter(), true);
    }

    // ============================================================
    // ESTADO 1 – Construtor inválido
    // ============================================================
    @Test
    public void deveFalharConstrutorComSocketNulo() {
        assertThrows(Exception.class, () -> {
            new Parceiro(null, receptor, transmissor);
        });
    }

    // ============================================================
    // ESTADO 2 – Buffer vazio
    // ============================================================
    @Test
    public void espieComBufferVazioDeveLerSemConsumir() throws Exception {
        Parceiro p = new Parceiro(socket, receptor, transmissor);

        pipeWriter.write("MSG1\n");
        pipeWriter.flush();

        String primeira = p.espie(); // lê do receptor
        String segunda = p.espie();  // vem do buffer

        assertEquals("MSG1", primeira);
        assertEquals("MSG1", segunda);
    }

    @Test
    public void envieComBufferVazioDeveLerEConsumir() throws Exception {
        Parceiro p = new Parceiro(socket, receptor, transmissor);

        pipeWriter.write("OLA\n");
        pipeWriter.flush();

        String retorno = p.envie();

        assertEquals("OLA", retorno);
        assertNull(getPrivate(p, "proximoComunicado"));
    }

    // ============================================================
    // ESTADO 3 – Buffer cheio
    // ============================================================
    @Test
    public void espieNaoDeveRelerQuandoBufferCheio() throws Exception {
        Parceiro p = new Parceiro(socket, receptor, transmissor);

        pipeWriter.write("AB\n");
        pipeWriter.flush();

        p.espie(); // preenche o buffer
        String segunda = p.espie(); // não relê do receptor

        assertEquals("AB", segunda);
    }

    @Test
    public void envieComBufferCheioDeveConsumirBuffer() throws Exception {
        Parceiro p = new Parceiro(socket, receptor, transmissor);

        pipeWriter.write("XYZ\n");
        pipeWriter.flush();

        p.espie(); // buffer cheio

        String retorno = p.envie();

        assertEquals("XYZ", retorno);
        assertNull(getPrivate(p, "proximoComunicado"));
    }

    // ============================================================
    // ESTADO 4 – Testando adeus()
    // ============================================================
    @Test
    public void adeusDeveFecharTudo() throws Exception {
        Parceiro p = new Parceiro(socket, receptor, transmissor);
        p.adeus();

        assertTrue(socket.isClosed());
    }

    // ============================================================
    // Teste receba()
    // ============================================================
    @Test
    public void recebaDeveEnviarMensagem() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);

        Parceiro p = new Parceiro(socket, receptor, pw);

        p.receba("{teste:1}");

        assertTrue(sw.toString().contains("{teste:1}"));
    }

    // ============================================================
    // Acesso a campo privado para validar o estado interno
    // ============================================================
    private Object getPrivate(Object obj, String nome) throws Exception {
        var campo = obj.getClass().getDeclaredField(nome);
        campo.setAccessible(true);
        return campo.get(obj);
    }

}