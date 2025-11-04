package org.example;

public class TratadoraDeComunicadoDeDesligamento extends Thread {
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento(Parceiro var1) throws Exception {
        if (var1 == null) {
            throw new Exception("Porta invalida");
        } else {
            this.servidor = var1;
        }
    }

    public void run() {
        while(true) {
            try {
                if (this.servidor.espie() instanceof ComunicadoDeDesligamento) {
                    System.out.println("\nO servidor vai ser desligado agora;");
                    System.err.println("volte mais tarde!\n");
                    System.exit(0);
                }
            } catch (Exception var2) {
            }
        }
    }
}
