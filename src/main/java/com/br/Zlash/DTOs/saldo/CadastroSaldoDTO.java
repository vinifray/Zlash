package com.br.Zlash.DTOs.saldo;

import com.br.Zlash.models.Saldo;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroSaldoDTO {
    @CPF(message = "{validacao.cpf}")
    private String cpf;
    private double saldoInicial;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Saldo converterDTOParaModel(){
        Saldo saldo = new Saldo();
        saldo.setValor(this.saldoInicial);
        saldo.setCpf(this.cpf);

        return saldo;
    }
}
