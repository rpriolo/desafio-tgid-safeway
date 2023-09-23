package br.com.tgid.safeway.exception;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException(String mensagem) {
        super(mensagem);
    }
}
