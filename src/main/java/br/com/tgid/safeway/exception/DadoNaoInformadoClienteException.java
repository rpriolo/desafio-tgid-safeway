package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que algum dado obrigatório para o cadastro de um cliente não foi informado
 * no corpo da requisição.
 */
public class DadoNaoInformadoClienteException extends RuntimeException {
    public DadoNaoInformadoClienteException(String mensagem) {
        super(mensagem);
    }
}
