package br.com.tgid.safeway.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaqueEfetivadoDTO(TipoTransacao tipo,
                                Long idCliente,
                                Long idEmpresa,
                                BigDecimal valorBase,
                                BigDecimal taxaRecolhida,
                                BigDecimal valorEfetivado,
                                LocalDateTime dataHora) {

    public SaqueEfetivadoDTO(Transacao transacao) {
        this(
                transacao.getTipo(),
                transacao.getCliente().getId(),
                transacao.getEmpresa().getId(),
                transacao.getValor(),
                transacao.getValor().multiply(transacao.getEmpresa().getTaxaAdministracao()),
                transacao.getValor().add(transacao.getValor().multiply(transacao.getEmpresa().getTaxaAdministracao())),
                transacao.getDataHora()
        );
    }

}
