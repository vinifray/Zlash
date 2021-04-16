package com.br.Zlash.controllers;

import com.br.Zlash.DTOs.saldo.CadastroSaldoDTO;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.services.SaldoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Saldo cadastrarSaldo(@RequestBody @Valid CadastroSaldoDTO cadastroSaldoDTO){
        Saldo saldo = cadastroSaldoDTO.converterDTOParaModel();
        return saldoService.cadastrarSaldo(saldo);
    }
}
