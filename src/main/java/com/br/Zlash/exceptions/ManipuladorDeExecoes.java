package com.br.Zlash.exceptions;

import com.br.Zlash.exceptions.erros.ObjetoDeErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ManipuladorDeExecoes extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(400).body(criarObjetosDeErro(ex));
    }

    private List<ObjetoDeErro> criarObjetosDeErro(MethodArgumentNotValidException exception){
        List<ObjetoDeErro> erros = exception.getBindingResult().getFieldErrors().stream()
                .map(erro -> new ObjetoDeErro(erro.getDefaultMessage())).collect(Collectors.toList());

        return erros;
    }

    @ExceptionHandler(ExcecaoCustomizada.class)
    public ResponseEntity<ObjetoDeErro> manipularExecoesBase(ExcecaoCustomizada ex){
        ObjetoDeErro objetoDeErro = new ObjetoDeErro(ex.getMessage());

        return ResponseEntity.status(ex.getStatusCode()).body(objetoDeErro);
    }
}
