package br.com.tgid.safeway.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @param tipo O tipo da transação realizada, podendo ser depósito ou saque.
 * @param idCliente O identificador único gerado pelo banco de dados que se refere a um cliente.
 * @param idEmpresa O identificador único gerado pelo banco de dados que se refere a uma empresa.
 * @param valor O montante em reais (R$) transacionado entre as partes.
 * @param dataHora A data (AAAA-MM-DD) e hora (HH:MM:SS) em que a transação ocorreu.
 */
public record TransacaoDTO(TipoTransacao tipo,
                           Long idCliente,
                           Long idEmpresa,
                           BigDecimal valor,
                           LocalDateTime dataHora) {
}
