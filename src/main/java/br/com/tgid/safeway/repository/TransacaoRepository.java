package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
