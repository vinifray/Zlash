package com.br.Zlash.controllers;

import com.br.Zlash.DTOs.conta.CadastroContaDTO;
import com.br.Zlash.DTOs.saldo.SaldoDTO;
import com.br.Zlash.enuns.StatusConta;
import com.br.Zlash.models.Conta;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.services.ContaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ContaController.class)
public class ContaControllerTest {

    @MockBean
    private ContaService contaService;

    @Autowired
    private MockMvc mockMvc;

    private CadastroContaDTO cadastroContaDTO;
    private Conta conta;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        this.cadastroContaDTO = new CadastroContaDTO();
        this.cadastroContaDTO.setStatus(StatusConta.PAGO);
        this.cadastroContaDTO.setDescricao("Xablau");
        this.cadastroContaDTO.setValor(100.00);

        SaldoDTO saldoDTO = new SaldoDTO();
        saldoDTO.setCpf("069.358.630-31");
        this.cadastroContaDTO.setSaldo(saldoDTO);

        this.conta = new Conta();
        this.conta.setValor(100);
        this.conta.setDescricao("Qualquer outra coisa");
        this.conta.setStatus(StatusConta.PAGO);
        this.conta.setId(8001);

        Saldo saldo = new Saldo();
        saldo.setValor(1000);
        saldo.setCpf("414.033.850-41");
        this.conta.setSaldo(saldo);

        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testarCadastroDeContaCaminhoPositivo() throws Exception {
        Mockito.when(contaService.cadastrarConta(Mockito.any(Conta.class))).thenReturn(this.conta);
        String contaJson = objectMapper.writeValueAsString(this.cadastroContaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contaJson)).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(this.conta.getId())));

    }

}
