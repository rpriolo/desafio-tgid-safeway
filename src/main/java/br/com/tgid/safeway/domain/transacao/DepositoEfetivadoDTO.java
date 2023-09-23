package br.com.tgid.safeway.domain.transacao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;

public record DepositoEfetivadoDTO(TipoTransacao tipo,
                                   Long idCliente,
                                   Long idEmpresa,
                                   BigDecimal valorBase,
                                   BigDecimal taxaRecolhida,
                                   BigDecimal valorEfetivado,
                                   LocalDateTime dataHora) {

    public DepositoEfetivadoDTO(Transacao transacao) {
        this(
                transacao.getTipo(),
                transacao.getCliente().getId(),
                transacao.getEmpresa().getId(),
                transacao.getValor(),
                transacao.getValor().multiply(transacao.getEmpresa().getTaxaAdministracao()),
                transacao.getValor().subtract(transacao.getValor().multiply(transacao.getEmpresa().getTaxaAdministracao())),
                transacao.getDataHora()
        );
    }

}
