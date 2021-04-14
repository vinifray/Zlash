package com.br.Zlash.exceptions.erros;

public class ObjetoDeErro {
    private String mensagem;

    public ObjetoDeErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
