package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que já existe um cliente cadastrado com o CPF informado.
 */
public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException(String mensagem) {
        super(mensagem);
    }
}
