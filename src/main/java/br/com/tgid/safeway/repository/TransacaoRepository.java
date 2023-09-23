package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe responsável por providenciar métodos de manipulação de transações junto ao banco de dados.
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
