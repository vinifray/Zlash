package com.br.Zlash.controllers;

import com.br.Zlash.DTOs.conta.CadastroContaDTO;
import com.br.Zlash.models.Conta;
import com.br.Zlash.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    public ContaService contaService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrarConta(@RequestBody @Valid CadastroContaDTO contaDTO){
        Conta conta = contaDTO.converterDTOparaModel();
        return contaService.cadastrarConta(conta);
    }
}
