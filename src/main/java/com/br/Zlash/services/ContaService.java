package com.br.Zlash.services;

import com.br.Zlash.enuns.StatusConta;
import com.br.Zlash.exceptions.SaldoInsuficienteException;
import com.br.Zlash.models.Conta;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private SaldoService saldoService;

    public Conta cadastrarConta(Conta conta){
        conta.setSaldo(saldoService.buscarPorCPF(conta.getSaldo().getCpf()));
        if (conta.getStatus() == StatusConta.PAGO){
            calcularSaldo(conta);
        }
        return contaRepository.save(conta);
    }

    public void verificarSaldo(Conta conta){
        if(conta.getSaldo().getValor() < conta.getValor()){
            throw new SaldoInsuficienteException();
        }
    }

    public void calcularSaldo(Conta conta){
        Saldo saldo = conta.getSaldo();

        verificarSaldo(conta);
        saldoService.debitarSaldo(saldo, conta.getValor());
    }
}
