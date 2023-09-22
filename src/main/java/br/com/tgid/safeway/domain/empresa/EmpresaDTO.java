package br.com.tgid.safeway.domain.empresa;

import java.math.BigDecimal;

public record EmpresaDTO(String cnpj,
                         BigDecimal saldo,
                         BigDecimal taxaAdministracao) {
}