package br.com.tgid.safeway.exception;

public class DadoNaoInformadoClienteException extends RuntimeException {
    public DadoNaoInformadoClienteException(String mensagem) {
        super(mensagem);
    }
}
