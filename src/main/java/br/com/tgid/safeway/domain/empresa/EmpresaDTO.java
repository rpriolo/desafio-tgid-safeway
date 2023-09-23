package br.com.tgid.safeway.domain.empresa;

import java.math.BigDecimal;

/**
 * @param cnpj O documento de um cliente (pessoa física). Deve conter 14 dígitos.
 * @param saldo O montante em reais (R$) disponível na conta da empresa.
 * @param taxaAdministracao A porcentagem (%) referente à utilização do
 *                          sistema/serviço cobrada junto à empresa sobre
 *                          todas as transações realizadas.
 */
public record EmpresaDTO(String cnpj,
                         BigDecimal saldo,
                         BigDecimal taxaAdministracao) {
}