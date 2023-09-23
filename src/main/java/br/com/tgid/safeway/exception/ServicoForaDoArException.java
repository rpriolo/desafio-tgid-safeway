package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que o serviço de envio de notificações às empresas está indisponível.
 */
public class ServicoForaDoArException extends RuntimeException {
    public ServicoForaDoArException(String mensagem) {
        super(mensagem);
    }
}
