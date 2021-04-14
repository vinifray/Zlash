package com.br.Zlash.exceptions;

public class SaldoInsuficienteException extends ExcecaoCustomizada{
    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(String message, int statusCode) {
        super(message, statusCode);
    }

    public SaldoInsuficienteException() {
        super("Saldo insuficiente", 422);
    }
}
