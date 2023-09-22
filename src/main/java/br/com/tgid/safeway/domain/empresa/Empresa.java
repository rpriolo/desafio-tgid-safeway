package br.com.tgid.safeway.domain.empresa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "empresas")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private BigDecimal saldo;
    private BigDecimal taxaAdministracao;

    public Empresa(EmpresaDTO dadosEmpresa) {
        this.cnpj = dadosEmpresa.cnpj();
        this.saldo = dadosEmpresa.saldo();
        this.taxaAdministracao = dadosEmpresa.taxaAdministracao();
    }
}
