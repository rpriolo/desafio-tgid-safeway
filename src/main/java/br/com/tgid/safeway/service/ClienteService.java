package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.cliente.Cliente;
import br.com.tgid.safeway.domain.cliente.ClienteDTO;
import br.com.tgid.safeway.exception.ClienteExistenteException;
import br.com.tgid.safeway.exception.CpfInvalidoException;
import br.com.tgid.safeway.exception.DadoNaoInformadoClienteException;
import br.com.tgid.safeway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por processar as lógicas de negócio a partir dos dados recebidos no controller de clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoService transacaoService;

    /**
     * Recebe as informações da requisição HTTP e cadastra um cliente no banco de dados.
     *
     * @param dadosCliente O conjunto de dados que descrevem e compõem um cliente.
     */
    public void cadastrarCliente(ClienteDTO dadosCliente) {
        Cliente cliente = new Cliente(dadosCliente);
        clienteRepository.save(cliente);
    }

    /**
     * Recebe as informações da requisição HTTP e faz as validações necessárias para o cadastro de clientes.
     *
     * @param dadosCliente O conjunto de dados que descrevem e compõem um cliente.
     */
    public void validarRequisicao(ClienteDTO dadosCliente) {
        if (clienteRepository.existsByCpf(dadosCliente.cpf())) {
            throw new ClienteExistenteException("O CPF informado já está cadastrado na base de dados");
        }
        if (dadosCliente.cpf() == null) {
            throw new DadoNaoInformadoClienteException("É necessário informar o CPF para cadastrar um cliente");
        }
        if (dadosCliente.cpf().length() != 11) {
            throw new CpfInvalidoException("O CPF deve ter 11 dígitos");
        }
    }
}
