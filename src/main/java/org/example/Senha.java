package org.example;

import java.util.regex.*;

public class Senha {
    protected String senha;
    protected static final String regexSenha = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*()])(?=\\S+$).{8,16}";
    protected static final Pattern padraoSenha = Pattern.compile(regexSenha);

    public Senha(String senha) {
        this.senha = senha;
    }

    public boolean isSenhaValida() {
        // Tem que conter ao menos um desses caracteres: A-Z, a-z, 0-9, !@#$%&*()
        // Min. 8 caracteres, Max. 16 caracteres.
        if(this.senha == null) return false;

        return padraoSenha.matcher(this.senha).matches();
    }
}
