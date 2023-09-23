package br.com.tgid.safeway.exception;

/**
 * @param mensagem   A mensagem que será exibida na resposta da requisição mal-sucedida.
 * @param statusCode O código HTTP referente ao erro apresentado.
 */
public record ExceptionDTO(String mensagem,
                           String statusCode) {
}
