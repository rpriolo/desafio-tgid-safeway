package br.com.tgid.safeway.exception;

public class TransacaoInvalidaException extends RuntimeException {
    public TransacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
