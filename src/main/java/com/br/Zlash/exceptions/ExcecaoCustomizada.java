package com.br.Zlash.exceptions;

public class ExcecaoCustomizada extends RuntimeException{
    private int statusCode;


    public ExcecaoCustomizada(String message) {
        super(message);
    }

    public ExcecaoCustomizada(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
