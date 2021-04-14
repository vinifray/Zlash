package com.br.Zlash.controllers;

import com.br.Zlash.models.Saldo;
import com.br.Zlash.services.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saldos")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/")
    public Saldo buscarPorCPF(@RequestParam(name = "cpf") String cpf){
        Saldo saldo = saldoService.buscarPorCPF(cpf);
        return saldo;
    }
}
