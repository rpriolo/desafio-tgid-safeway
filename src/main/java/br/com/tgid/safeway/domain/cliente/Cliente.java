package br.com.tgid.safeway.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Representa um cliente no contexto do sistema. Pode realizar depósitos e saques junto às empresas.
 */
@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    /**
     * O ID é único e gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O CPF é um valor único no banco de dados.
     */
    private String cpf;

    public Cliente(ClienteDTO dadosCliente) {
        this.cpf = dadosCliente.cpf();
    }
}
