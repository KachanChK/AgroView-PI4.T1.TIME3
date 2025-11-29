package org.example;

import java.util.regex.*;

public class Email {
    protected String email;
    protected static final String regexEmail = "^[\\p{L}0-9._%+-]+@[\\p{L}0-9.-]+\\.[\\p{L}]{2,}$";
    protected static final Pattern padraoEmail = Pattern.compile(regexEmail);

    public Email(String email){
        this.email = email;
    }

    public boolean isEmailValido() {
        // ESTRUTURA DO REGEX --  "[parte 1] + @ + [parte 2] + . + [parte 3]" Min = 2 caracteres
        /*
        parte 1: Deve conter letras em qualquer lingua ("ã" é válido), número qualquer ou (._%+-) 
        parte 2: Deve conter letras em qualquer lingua, número ou (._)
        parte 3: Deve conter letras em qualquer lingua 
        */

        if(this.email == null) return false;
        return padraoEmail.matcher(this.email).matches();
    }
}
