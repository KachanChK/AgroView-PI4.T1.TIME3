package org.example;

public class TratadoraDeComunicadoDeDesligamento extends Thread {
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) throws Exception {
        if (servidor == null)
            throw new Exception("Servidor inválido");

        this.servidor = servidor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object recebido = this.servidor.espie(); // Olha o próximo objeto sem consumir

                // Agora trabalha apenas com String
                if (recebido instanceof String mensagem) {

                    if (mensagem.equals("DESLIGAMENTO")) {
                        System.out.println("\nO servidor vai ser desligado agora;");
                        System.err.println("volte mais tarde!\n");
                        System.exit(0);
                    }

                }

            } catch (Exception e) {
                // ignorar exceção de espie() vazio
            }
        }
    }
}