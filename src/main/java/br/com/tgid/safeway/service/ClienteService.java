package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.cliente.Cliente;
import br.com.tgid.safeway.domain.cliente.ClienteDTO;
import br.com.tgid.safeway.domain.transacao.TransacaoDTO;
import br.com.tgid.safeway.exception.ClienteExistenteException;
import br.com.tgid.safeway.exception.CpfInvalidoException;
import br.com.tgid.safeway.exception.DadoNaoInformadoClienteException;
import br.com.tgid.safeway.exception.EmpresaExistenteException;
import br.com.tgid.safeway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoService transacaoService;

    public void cadastrarCliente(ClienteDTO dadosCliente) {
        Cliente cliente = new Cliente(dadosCliente);
        clienteRepository.save(cliente);
    }

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

    public void depositar(TransacaoDTO dadosTransacao) {
        transacaoService.efetivarDeposito(dadosTransacao);
    }

    public void sacar(TransacaoDTO dadosTransacao) {
        transacaoService.efetivarSaque(dadosTransacao);
    }

}
