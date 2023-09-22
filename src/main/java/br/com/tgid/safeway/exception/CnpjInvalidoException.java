package br.com.tgid.safeway.exception;

public class CnpjInvalidoException extends RuntimeException {
    public CnpjInvalidoException(String mensagem) {
        super(mensagem);
    }
}
