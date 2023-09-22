package br.com.tgid.safeway.exception;

public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
