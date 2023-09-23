package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe responsável por tratar os erros apresentados durante a utilização da API para cadastro de empresas.
 */
@RestControllerAdvice
public class TratadorErrosEmpresa {
    /**
     * Trata requisições que não contém CNPJ, saldo e taxa de administração da empresa a ser cadastrada.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(DadoNaoInformadoEmpresaException.class)
    public ResponseEntity dadoNaoInformadoEmpresa() {
        ExceptionDTO ex = new ExceptionDTO("É necessário informar CNPJ, saldo e taxa de administração para cadastrar uma empresa", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições que contém um CNPJ de empresa a ser cadastrada que já existe no banco de dados.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(EmpresaExistenteException.class)
    public ResponseEntity empresaExistente() {
        ExceptionDTO ex = new ExceptionDTO("O CNPJ informado já está cadastrado na base de dados", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições que não contém um CNPJ preenchido com 14 dígitos.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(CnpjInvalidoException.class)
    public ResponseEntity cnpjInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O CNPJ deve ter 14 dígitos", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições em que o saldo da empresa a ser cadastrada é negativo.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(SaldoInvalidoException.class)
    public ResponseEntity saldoInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O saldo deve ser maior ou igual a zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições em que a taxa de administração da empresa a ser cadastrada é negativa.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(TaxaInvalidaException.class)
    public ResponseEntity taxaInvalida() {
        ExceptionDTO ex = new ExceptionDTO("A taxa de administração deve ser maior ou igual a zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    /**
     * Trata requisições em que o saldo em reais da empresa é insuficiente para efetivar uma transação.
     *
     * @return Retorna uma resposta com mensagem explicativa sobre o erro e o código de status HTTP.
     */
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity saldoInsuficiente() {
        ExceptionDTO ex = new ExceptionDTO("A empresa não tem saldo suficiente para efetivar essa transação", "400");
        return ResponseEntity.badRequest().body(ex);
    }

}
