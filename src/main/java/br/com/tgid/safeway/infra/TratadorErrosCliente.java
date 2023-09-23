package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ClienteExistenteException;
import br.com.tgid.safeway.exception.CpfInvalidoException;
import br.com.tgid.safeway.exception.DadoNaoInformadoClienteException;
import br.com.tgid.safeway.exception.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe responsável por tratar os erros apresentados durante a utilização da API para cadastro de clientes.
 */
@RestControllerAdvice
public class TratadorErrosCliente {
    /**
     * Trata requisições que não contém o CPF do cliente a ser cadastrado.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(DadoNaoInformadoClienteException.class)
    public ResponseEntity dadoNaoInformadoCliente() {
        ExceptionDTO ex = new ExceptionDTO("É necessário informar o CPF para cadastrar um cliente", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições que contém um CPF de cliente a ser cadastrado que já existe no banco de dados.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity clienteExistente() {
        ExceptionDTO ex = new ExceptionDTO("O CPF informado já está cadastrado na base de dados", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições que não contém um CPF preenchido com 11 dígitos.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity cpfInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O CPF deve ter 11 dígitos", "400");
        return ResponseEntity.badRequest().body(ex);
    }
}
