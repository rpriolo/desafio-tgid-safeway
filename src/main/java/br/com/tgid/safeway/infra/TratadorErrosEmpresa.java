package br.com.tgid.safeway.infra;

import br.com.tgid.safeway.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrosEmpresa {
    @ExceptionHandler(DadoNaoInformadoEmpresaException.class)
    public ResponseEntity dadoNaoInformadoEmpresa() {
        ExceptionDTO ex = new ExceptionDTO("É necessário informar CNPJ, saldo e taxa de administração para cadastrar uma empresa", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(CnpjInvalidoException.class)
    public ResponseEntity cnpjInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O CNPJ deve ter 14 dígitos", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(SaldoInvalidoException.class)
    public ResponseEntity saldoInvalido() {
        ExceptionDTO ex = new ExceptionDTO("O saldo deve ser maior ou igual a zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(TaxaInvalidaException.class)
    public ResponseEntity taxaInvalida() {
        ExceptionDTO ex = new ExceptionDTO("A taxa de administração deve ser maior ou igual a zero", "400");
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity saldoInsuficiente() {
        ExceptionDTO ex = new ExceptionDTO("A empresa não tem saldo suficiente para efetivar essa transação", "400");
        return ResponseEntity.badRequest().body(ex);
    }

}
