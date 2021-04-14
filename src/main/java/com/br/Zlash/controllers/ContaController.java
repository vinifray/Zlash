package com.br.Zlash.controllers;

import com.br.Zlash.DTOs.saldo.CadastroContaDTO;
import com.br.Zlash.models.Conta;
import com.br.Zlash.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    public ContaService contaService;

    @PostMapping("/")
    public Conta cadastrarConta(@RequestBody CadastroContaDTO contaDTO){
        Conta conta = contaDTO.converterDTOparaModel();
        return contaService.cadastrarConta(conta);
    }
}
