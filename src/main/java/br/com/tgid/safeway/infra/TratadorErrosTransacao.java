package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ExceptionDTO;
import br.com.tgid.safeway.exception.TransacaoInvalidaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrosTransacao {
    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity valorInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O valor da transação precisa ser maior que zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }
}
