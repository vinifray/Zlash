package com.br.Zlash.controllers;

import com.br.Zlash.DTOs.saldo.CadastroSaldoDTO;
import com.br.Zlash.models.Saldo;
import com.br.Zlash.services.SaldoService;
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

@WebMvcTest(SaldoController.class)
public class SaldoControllerTest {

    @MockBean
    private SaldoService saldoService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private CadastroSaldoDTO cadastroSaldoDTO;
    private Saldo saldo;

    @BeforeEach
    public void setUp(){
        this.cadastroSaldoDTO = new CadastroSaldoDTO();
        this.cadastroSaldoDTO.setCpf("008.898.690-06");
        this.cadastroSaldoDTO.setSaldoInicial(1000.00);

        this.objectMapper = new ObjectMapper();

        this.saldo = new Saldo();
        this.saldo.setCpf("008.898.690-06");
        this.saldo.setValor(1000.00);
    }

    @Test
    public void testarCadastroDeSaldoCaminhoPositivo() throws Exception{
        Mockito.when(saldoService.cadastrarSaldo(Mockito.any(Saldo.class))).thenReturn(this.saldo);

        String saldoJson = objectMapper.writeValueAsString(this.cadastroSaldoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/saldos/")
                .contentType(MediaType.APPLICATION_JSON).content(saldoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testarCadastroDeSaldoCPFInvalido() throws Exception{
        this.cadastroSaldoDTO.setCpf("xablau");
        Mockito.when(saldoService.cadastrarSaldo(Mockito.any(Saldo.class))).thenReturn(this.saldo);

        String saldoJson = objectMapper.writeValueAsString(this.cadastroSaldoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/saldos/")
                .contentType(MediaType.APPLICATION_JSON).content(saldoJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$[0].mensagem", CoreMatchers.equalTo("{validacao.cpf}")
                        ));

    }

}
