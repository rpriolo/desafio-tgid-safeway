package br.com.tgid.safeway.domain.notificacao;

import br.com.tgid.safeway.domain.transacao.TipoTransacao;

import java.math.BigDecimal;

public record NotificacaoDTO(String mensagem, TipoTransacao tipo, BigDecimal valor) {
}
