package com.br.Zlash.DTOs.saldo;

import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Min;

public class CadastroSaldoDTO {
    @CPF(message = "{validacao.cpf}")
    private String cpf;
    @Min(value = 100, message = "{validacao.saldo_minimo}")
    private double valor;

    public CadastroSaldoDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
