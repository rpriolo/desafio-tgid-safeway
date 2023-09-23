package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Boolean existsByCnpj(String cnpj);
}
