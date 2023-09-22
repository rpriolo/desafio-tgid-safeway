package br.com.tgid.safeway.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(TipoTransacao tipo,
                           Long idCliente,
                           Long idEmpresa,
                           BigDecimal valor,
                           LocalDateTime dataHora) {
}
