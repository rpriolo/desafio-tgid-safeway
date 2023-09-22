package br.com.tgid.safeway.exception;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }
}
