package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ClienteExistenteException;
import br.com.tgid.safeway.exception.CpfInvalidoException;
import br.com.tgid.safeway.exception.DadoNaoInformadoClienteException;
import br.com.tgid.safeway.exception.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrosCliente {
    @ExceptionHandler(DadoNaoInformadoClienteException.class)
    public ResponseEntity dadoNaoInformadoCliente() {
        ExceptionDTO ex = new ExceptionDTO("É necessário informar o CPF para cadastrar um cliente", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity clienteExistente() {
        ExceptionDTO ex = new ExceptionDTO("O CPF informado já está cadastrado na base de dados", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity cpfInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O CPF deve ter 11 dígitos", "400");
        return ResponseEntity.badRequest().body(ex);
    }
}
