package org.example;



import java.io.*;

public class Teclado {

    private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    public static String getUmString(){
        String ret = null;

        try{
            ret = teclado.readLine();
        }catch(IOException e){}
        return ret;
    }

    public static byte getUmByte() throws Exception{
        byte ret = (byte)0;
        try{
            ret = Byte.parseByte(teclado.readLine());
        }catch (NumberFormatException e){
            throw new Exception("Byte Invalido");
        }
        return ret;
    }

    public static int getUmInt() throws Exception{
        int ret = 0;
        try{
            Integer.parseInt(teclado.readLine());
        }
        catch(NumberFormatException e){
            throw new Exception("Int Invalido");
        }
        return ret;
    }

    public static char getUmChar() throws Exception{
        char ret = ' ';
        try{
            String str = teclado.readLine();

            if(str.length() != 1){
                throw new Exception("Char Invalido");
            }
            ret = str.charAt(0);
        } catch(IOException e){}
        return ret;
    }
}


