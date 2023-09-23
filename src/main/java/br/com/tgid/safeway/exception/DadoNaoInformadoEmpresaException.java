package br.com.tgid.safeway.exception;

/**
 * Exceção lançada em tempo de execução quando a aplicação identifica
 * que algum dado obrigatório para o cadastro de uma empresa não foi informado
 * no corpo da requisição.
 */
public class DadoNaoInformadoEmpresaException extends RuntimeException {
    public DadoNaoInformadoEmpresaException(String mensagem) {
        super(mensagem);
    }
}
