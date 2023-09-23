package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe responsável por providenciar métodos de manipulação de empresas junto ao banco de dados.
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    /**
     * Verifica através do CNPJ se a empresa informada já existe na base de dados.
     *
     * @param cnpj O documento de uma empresa (pessoa jurídica). Deve conter 14 dígitos.
     * @return true caso exista ou false caso não exista na base de dados.
     */
    Boolean existsByCnpj(String cnpj);
}
