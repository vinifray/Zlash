package com.br.Zlash.services;

import com.br.Zlash.enuns.StatusConta;
import com.br.Zlash.exceptions.SaldoInsuficienteException;
import com.br.Zlash.models.Conta;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ContaServiceTest {
    @Autowired
    private ContaService contaService;

    @MockBean
    private ContaRepository contaRepository;
    @MockBean
    private SaldoService saldoService;

    private Conta conta;
    private Saldo saldo;

    @BeforeEach
    public void setUp(){
        this.saldo = new Saldo();
        saldo.setValor(1000);
        saldo.setCpf("236.718.880-75");

        this.conta = new Conta();
        this.conta.setSaldo(saldo);
        this.conta.setStatus(StatusConta.PAGO);
        this.conta.setValor(100);
        this.conta.setDescricao("Compra de marmita");
    }

    @Test
    public void testarValidarSaldo(){
        this.conta.setValor(10000);

        Assertions.assertThrows(SaldoInsuficienteException.class, () -> {
            contaService.verificarSaldo(this.conta);
        });
    }

    @Test
    public void testarCalcularSaldo(){
        contaService.calcularSaldo(this.conta);

        Mockito.verify(saldoService, Mockito.times(1)).
                debitarSaldo(Mockito.any(Saldo.class), Mockito.anyDouble());
    }

    @Test
    public void testarCadastrarContaQueNaoFoiPaga(){
        Mockito.when(saldoService.buscarPorCPF(Mockito.anyString())).thenReturn(this.saldo);
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(this.conta);

        Conta conta = contaService.cadastrarConta(this.conta);

        Mockito.verify(contaRepository, Mockito.times(1)).save(this.conta);
    }

    @Test
    public void testarCadastrarContaQueFoiPaga(){
        Mockito.when(saldoService.buscarPorCPF(Mockito.anyString())).thenReturn(this.saldo);
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(this.conta);

        Conta conta = contaService.cadastrarConta(this.conta);

        Mockito.verify(contaRepository, Mockito.times(1)).save(this.conta);
    }
}
