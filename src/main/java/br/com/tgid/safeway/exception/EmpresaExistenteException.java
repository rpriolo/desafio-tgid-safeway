package br.com.tgid.safeway.exception;

public class EmpresaExistenteException extends RuntimeException {
    public EmpresaExistenteException(String mensagem) {
        super(mensagem);
    }
}
