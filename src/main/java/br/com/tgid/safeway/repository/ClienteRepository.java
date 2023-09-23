package br.com.tgid.safeway.repository;

import br.com.tgid.safeway.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe responsável por providenciar métodos de manipulação de clientes junto ao banco de dados.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Verifica através do CPF se o cliente informado já existe na base de dados.
     *
     * @param cpf O documento de um cliente (pessoa física). Deve conter 11 dígitos.
     * @return true caso exista ou false caso não exista na base de dados.
     */
    Boolean existsByCpf(String cpf);
}
