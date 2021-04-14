package com.br.Zlash.DTOs.conta;

import com.br.Zlash.DTOs.saldo.SaldoDTO;
import com.br.Zlash.enuns.StatusConta;
import com.br.Zlash.models.Conta;
import com.br.Zlash.models.Saldo;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class CadastroContaDTO {

    @NotBlank()
    private double valor;
    @NotBlank()
    private String descricao;
    @NotBlank
    private StatusConta status;

    @ManyToOne
    private SaldoDTO saldo;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusConta getStatus() {
        return status;
    }

    public void setStatus(StatusConta status) {
        this.status = status;
    }

    public SaldoDTO getSaldo() {
        return saldo;
    }

    public void setSaldo(SaldoDTO saldo) {
        this.saldo = saldo;
    }

    public Conta converterDTOparaModel(){
        Conta conta = new Conta();
        conta.setDescricao(this.getDescricao());
        conta.setSaldo(this.saldo.converterDTOParaModel());
        conta.setValor(this.valor);
        conta.setStatus(this.status);

        return conta;
    }
}
