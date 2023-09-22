package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.transacao.TransacaoDTO;
import br.com.tgid.safeway.service.ClienteService;
import br.com.tgid.safeway.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @Transactional
    public void realizarTransacao(@RequestBody TransacaoDTO dadosTransacao) {
        transacaoService.validarTipoTransacao(dadosTransacao);
    }

}
