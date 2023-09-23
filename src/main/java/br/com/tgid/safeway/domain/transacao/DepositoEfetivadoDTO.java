package br.com.tgid.safeway.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @param tipo           O tipo da transação realizada, podendo ser depósito ou saque.
 * @param idCliente      O identificador único gerado pelo banco de dados que se refere a um cliente.
 * @param idEmpresa      O identificador único gerado pelo banco de dados que se refere a uma empresa.
 * @param valorBase      O valor em reais que foi depositado pelo cliente.
 * @param taxaRecolhida  O valor em reais referente à taxa de administração que será descontada do Valor Base.
 * @param valorEfetivado O valor em reais efetivamente recebido pela empresa.
 * @param dataHora       A data (AAAA-MM-DD) e hora (HH:MM:SS) em que a transação ocorreu.
 */
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
