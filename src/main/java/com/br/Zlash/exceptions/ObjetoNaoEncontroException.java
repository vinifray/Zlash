package com.br.Zlash.exceptions;

public class ObjetoNaoEncontroException extends ExcecaoCustomizada{


    public ObjetoNaoEncontroException(String message) {
        super(message, 400);
    }

    public ObjetoNaoEncontroException(String message, int statusCode) {
        super(message, statusCode);
    }

    public ObjetoNaoEncontroException() {
        super("Objeto não encontrado", 400);
    }
}
