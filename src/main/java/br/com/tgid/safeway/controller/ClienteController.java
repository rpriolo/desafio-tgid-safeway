package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.cliente.ClienteDTO;
import br.com.tgid.safeway.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody ClienteDTO dadosCliente) {
    }
}
