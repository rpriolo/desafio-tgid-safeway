package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que o saldo informado durante o cadastro de uma empresa é negativo.
 */
public class SaldoInvalidoException extends RuntimeException {
    public SaldoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
