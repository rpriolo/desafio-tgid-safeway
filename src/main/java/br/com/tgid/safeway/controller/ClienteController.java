package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.cliente.ClienteDTO;
import br.com.tgid.safeway.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO dadosCliente) {
        clienteService.validarRequisicao(dadosCliente);
        clienteService.cadastrarCliente(dadosCliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosCliente);
    }
}
