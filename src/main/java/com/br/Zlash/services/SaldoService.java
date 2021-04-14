package com.br.Zlash.services;

import com.br.Zlash.exceptions.ObjetoNaoEncontroException;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.repositories.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    public Saldo buscarPorCPF(String cpf){
        Optional<Saldo> saldo = saldoRepository.findById(cpf);

        if (saldo.isPresent()){
            return saldo.get();
        }

        throw new ObjetoNaoEncontroException();
    }

    public Saldo debitarSaldo(Saldo saldo, Double valor){
        Double resultado = saldo.getValor() - valor;
        saldo.setValor(resultado);
        saldoRepository.save(saldo);
        return saldo;
    }
}
