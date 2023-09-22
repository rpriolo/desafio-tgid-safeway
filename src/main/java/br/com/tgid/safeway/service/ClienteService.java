package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.cliente.Cliente;
import br.com.tgid.safeway.domain.cliente.ClienteDTO;
import br.com.tgid.safeway.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cadastrarCliente(ClienteDTO dadosCliente) {
        Cliente cliente = new Cliente(dadosCliente);
        clienteRepository.save(cliente);
    }
}
