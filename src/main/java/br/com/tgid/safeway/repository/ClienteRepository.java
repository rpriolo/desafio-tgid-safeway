package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByCpf(String cpf);
}
