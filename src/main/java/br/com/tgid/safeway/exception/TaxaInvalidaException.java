package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que a taxa de administração informada durante o cadastro de uma empresa é negativa.
 */
public class TaxaInvalidaException extends RuntimeException {
    public TaxaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
