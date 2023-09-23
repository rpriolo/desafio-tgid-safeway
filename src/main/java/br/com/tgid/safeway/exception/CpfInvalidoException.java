package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * o CPF informado não está em um formato válido, com 11 dígitos.
 */
public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }
}
