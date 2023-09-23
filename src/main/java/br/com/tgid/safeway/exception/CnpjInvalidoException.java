package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que já existe uma empresa cadastrada com o CNPJ informado.
 */
public class CnpjInvalidoException extends RuntimeException {
    public CnpjInvalidoException(String mensagem) {
        super(mensagem);
    }
}
