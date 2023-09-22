package br.com.tgid.safeway.exception;

public class SaldoInvalidoException extends RuntimeException {
    public SaldoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
