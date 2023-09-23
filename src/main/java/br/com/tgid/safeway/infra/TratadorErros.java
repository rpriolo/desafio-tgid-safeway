package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe responsável por tratar os erros apresentados durante a utilização da API.
 */
@RestControllerAdvice
public class TratadorErros {
    /**
     * Trata requisições que estão incompletas ou em formato inadequado.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity requisicaoIlegivel() {
        ExceptionDTO ex = new ExceptionDTO("Verifique se todos os campos foram preenchidos corretamente", "400");
        return ResponseEntity.badRequest().body(ex);
    }
}
