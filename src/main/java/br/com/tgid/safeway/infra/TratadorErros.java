package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity requisicaoIlegivel() {
        ExceptionDTO ex = new ExceptionDTO("Verifique se todos os campos foram preenchidos corretamente", "400");
        return ResponseEntity.badRequest().body(ex);
    }
}
