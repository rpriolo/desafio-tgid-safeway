package br.com.tgid.safeway.domain.notificacao;

import br.com.tgid.safeway.domain.transacao.TipoTransacao;

import java.math.BigDecimal;

/**
 * @param mensagem A mensagem que será exibida na notificação para a empresa
 * @param tipo O tipo da transação realizada, podendo ser depósito ou saque
 * @param valor O montante transacionado
 */
public record NotificacaoDTO(String mensagem, TipoTransacao tipo, BigDecimal valor) {
}
