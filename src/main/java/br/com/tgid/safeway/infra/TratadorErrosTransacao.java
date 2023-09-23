package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ExceptionDTO;
import br.com.tgid.safeway.exception.TransacaoInvalidaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorErrosTransacao {
    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity valorInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O valor da transação precisa ser maior que zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity empresaNaoExiste() {
        ExceptionDTO ex = new ExceptionDTO("O ID informado não existe na base de dados de empresas", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity clienteNaoExiste() {
        ExceptionDTO ex = new ExceptionDTO("O ID informado não existe na base de dados de clientes", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }




}
