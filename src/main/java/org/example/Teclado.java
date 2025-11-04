package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado {

    private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    public static String getUmString() {
        String ret = null;
        try {
            ret = teclado.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public static char getUmChar() {
        char ret = ' ';
        try {
            ret = Character.valueOf(teclado.readLine().charAt(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public static double getUmDouble() {
        double ret = 0.0;
        try {
            ret = Double.parseDouble(teclado.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}

