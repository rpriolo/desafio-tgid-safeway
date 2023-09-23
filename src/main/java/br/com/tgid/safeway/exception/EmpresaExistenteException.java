package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que já existe uma empresa cadastrado com o CNPJ informado.
 */
public class EmpresaExistenteException extends RuntimeException {
    public EmpresaExistenteException(String mensagem) {
        super(mensagem);
    }
}
