package br.com.tgid.safeway.domain.empresa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa uma empresa no contexto do sistema.
 */
@Table(name = "empresas")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
/* Representa uma empresa no sistema **/
public class Empresa {
    /**
     * O ID é único e gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O CNPJ é um valor único no banco de dados.
     */
    private String cnpj;

    /**
     * O saldo é o montante em reais (R$) disponível na conta da empresa.
     */
    private BigDecimal saldo;

    /**
     * A taxa de administração é uma porcentagem (%) referente à utilização do
     * sistema/serviço cobrada junto à empresa sobre todas as transações realizadas
     */
    private BigDecimal taxaAdministracao;

    public Empresa(EmpresaDTO dadosEmpresa) {
        this.cnpj = dadosEmpresa.cnpj();
        this.saldo = dadosEmpresa.saldo();
        this.taxaAdministracao = dadosEmpresa.taxaAdministracao();
    }

    /**
     * @param valorTransacionado Valor final após desconto/soma da taxa de administração
     */
    public void atualizarSaldo(BigDecimal valorTransacionado) {
        this.saldo = this.saldo.add(valorTransacionado);
    }

}
