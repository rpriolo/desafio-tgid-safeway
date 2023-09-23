package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.transacao.*;
import br.com.tgid.safeway.repository.ClienteRepository;
import br.com.tgid.safeway.repository.EmpresaRepository;
import br.com.tgid.safeway.service.NotificacaoService;
import br.com.tgid.safeway.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por gerenciar as requisições da API referentes às transações.
 */
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private NotificacaoService notificacaoService;

    /**
     * Valida e realiza uma transação a partir dos dados fornecidos. Caso seja realizada
     * com sucesso, envia uma notificação à empresa e um e-mail ao cliente.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     * @return Retorna um código de status HTTP e uma resposta com os dados da transação efetuada.
     */
    @PostMapping
    @Transactional
    public ResponseEntity realizarTransacao(@RequestBody TransacaoDTO dadosTransacao) {
        var cliente = clienteRepository.getReferenceById(dadosTransacao.idCliente());
        var empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        Transacao transacao = new Transacao(cliente, empresa, dadosTransacao);

        transacaoService.validarTipoTransacao(dadosTransacao);

        if (dadosTransacao.tipo().equals(TipoTransacao.DEPOSITO)) {
            notificacaoService.enviarCallback(dadosTransacao.tipo(), new DepositoEfetivadoDTO(transacao).valorEfetivado());
            notificacaoService.enviarEmail(dadosTransacao.tipo(), dadosTransacao.valor());
            return ResponseEntity.status(HttpStatus.CREATED).body(new DepositoEfetivadoDTO(transacao));
        }
        if (dadosTransacao.tipo().equals(TipoTransacao.SAQUE)) {
            notificacaoService.enviarCallback(dadosTransacao.tipo(), new SaqueEfetivadoDTO(transacao).valorEfetivado());
            notificacaoService.enviarEmail(dadosTransacao.tipo(), dadosTransacao.valor());
            return ResponseEntity.status(HttpStatus.CREATED).body(new SaqueEfetivadoDTO(transacao));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
