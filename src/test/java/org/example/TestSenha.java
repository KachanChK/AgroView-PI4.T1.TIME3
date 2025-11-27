package org.example;

import junit.*;
import org.junit.jupiter.api.Test;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;


public class TestSenha {

    @Test
    public void testeSenhaValida() {
        Senha s = new Senha("Aa1!aaaa");
        assertTrue(s.isSenhaValida());
    }

    @Test
    public void testeSemMaiuscula() {
        Senha s = new Senha("aa1!aaaa");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeSemMinuscula() {
        Senha s = new Senha("AA1!AAAA");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeSemDigito() {
        Senha s = new Senha("Aa!!aaaa");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeSemEspecial() {
        Senha s = new Senha("Aa1aaaaa");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeComEspaco() {
        Senha s = new Senha("Aa1!a aa");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeMuitoCurta() {
        Senha s = new Senha("Aa1!a");
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeMuitoLonga() {
        Senha s = new Senha("Aa1!aaaaaaaaaaaaaaa"); // >16
        assertFalse(s.isSenhaValida());
    }

    @Test
    public void testeNula() {
        Senha s = new Senha(null);
        assertFalse(s.isSenhaValida());
    }
}
