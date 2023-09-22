package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.transacao.TipoTransacao;
import br.com.tgid.safeway.domain.transacao.TransacaoDTO;
import br.com.tgid.safeway.exception.TransacaoInvalidaException;
import br.com.tgid.safeway.repository.ClienteRepository;
import br.com.tgid.safeway.repository.EmpresaRepository;
import br.com.tgid.safeway.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public void validarValor(TransacaoDTO dadosTransacao) {
        if (dadosTransacao.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransacaoInvalidaException("O valor da transação precisa ser maior que zero");
        }
    }

    public void validarTipoTransacao(TransacaoDTO dadosTransacao) {
        if (dadosTransacao.tipo() == TipoTransacao.DEPOSITO) {
            efetivarDeposito(dadosTransacao);
        } else if (dadosTransacao.tipo() == TipoTransacao.SAQUE) {
            efetivarSaque(dadosTransacao);
        }
    }
}
