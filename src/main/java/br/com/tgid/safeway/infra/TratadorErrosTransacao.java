package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ExceptionDTO;
import br.com.tgid.safeway.exception.TransacaoInvalidaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Classe responsável por tratar os erros apresentados durante a utilização da API para realização de transações financeiras.
 */
@RestControllerAdvice
public class TratadorErrosTransacao {
    /**
     * Trata requisições em que o valor da transação a ser realizada é menor ou igual a zero.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity valorInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O valor da transação precisa ser maior que zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições em que a empresa designada para realizar a transação não existe na base de dados.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity empresaNaoExiste() {
        ExceptionDTO ex = new ExceptionDTO("O ID informado não existe na base de dados de empresas", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    /**
     * Trata requisições em que o cliente designado para realizar a transação não existe na base de dados.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity clienteNaoExiste() {
        ExceptionDTO ex = new ExceptionDTO("O ID informado não existe na base de dados de clientes", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }


}
