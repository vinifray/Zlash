package com.br.Zlash.DTOs.saldo;

import com.br.Zlash.models.Saldo;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaldoDTO {

    @CPF(message = "{validacao.cpf}")
    @NotBlank(message = "{validacao.cpf_obrigatorio}")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Saldo converterDTOParaModel(){
        Saldo saldo = new Saldo();
        saldo.setCpf(this.cpf);
        return saldo;
    }
}
