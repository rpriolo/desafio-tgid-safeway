package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que o valor informado para a realização de uma transação financeira é zero ou negativo.
 */
public class TransacaoInvalidaException extends RuntimeException {
    public TransacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
