package br.com.tgid.safeway.domain.transacao;

import br.com.tgid.safeway.domain.cliente.Cliente;
import br.com.tgid.safeway.domain.empresa.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacoes")
@Entity(name = "Transacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {
    /**
     * O ID é único e gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * O tipo da transação realizada, podendo ser depósito ou saque.
     */
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    /**
     * O solicitante da transação.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    /**
     * A responsável por receber do e/ou pagar o solicitante da transação.
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    /**
     * O montante em reais (R$) transacionado entre as partes.
     */
    private BigDecimal valor;
    /**
     * A data (AAAA-MM-DD) e hora (HH:MM:SS) em que a transação ocorreu.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHora;

    public Transacao(Cliente cliente, Empresa empresa, TransacaoDTO dadosTransacao) {
        this.tipo = dadosTransacao.tipo();
        this.cliente = cliente;
        this.empresa = empresa;
        this.valor = dadosTransacao.valor();
        this.dataHora = LocalDateTime.now();
    }
}