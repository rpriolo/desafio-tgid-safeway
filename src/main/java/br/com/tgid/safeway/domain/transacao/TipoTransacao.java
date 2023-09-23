package br.com.tgid.safeway.domain.transacao;

/**
 * Os tipos de transação que podem ocorrer do ponto de vista do cliente.
 */
public enum TipoTransacao {
    /**
     * Transação em que o cliente deposita um valor à empresa, aumentando o saldo da mesma.
     */
    DEPOSITO,
    /**
     * Transação em que o cliente saca um valor junto à empresa, diminuindo o saldo da mesma.
     */
    SAQUE
}
