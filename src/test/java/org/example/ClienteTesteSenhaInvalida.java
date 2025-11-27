package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.net.*;

public class ClienteTesteSenhaInvalida {

    private void matarCliente() {
        try { new Socket("localhost", 1010).close(); }
        catch (Exception ignored) {}
    }


}