package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.cliente.Cliente;
import br.com.tgid.safeway.domain.empresa.Empresa;
import br.com.tgid.safeway.domain.transacao.TipoTransacao;
import br.com.tgid.safeway.domain.transacao.Transacao;
import br.com.tgid.safeway.domain.transacao.TransacaoDTO;
import br.com.tgid.safeway.exception.SaldoInsuficienteException;
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

    public BigDecimal recolherTaxa(TransacaoDTO dadosTransacao) {
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = dadosTransacao.valor().multiply(empresa.getTaxaAdministracao());
        return taxaRecolhida;
    }

    public void efetivarDeposito(TransacaoDTO dadosTransacao) {
        validarValor(dadosTransacao);

        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = recolherTaxa(dadosTransacao);
        BigDecimal valorTransacionado = dadosTransacao.valor().subtract(taxaRecolhida);
        empresa.atualizarSaldo(valorTransacionado);
        salvarTransacao(dadosTransacao);
    }

    public void validarSaldoEmpresa(TransacaoDTO dadosTransacao) {
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = dadosTransacao.valor().multiply(empresa.getTaxaAdministracao());
        BigDecimal valorTotalTransacao = dadosTransacao.valor().add(taxaRecolhida);

        if (empresa.getSaldo().compareTo(valorTotalTransacao) < 0) {
            throw new SaldoInsuficienteException("A empresa não tem saldo suficiente para efetivar essa transação");
        }
    }

    public void efetivarSaque(TransacaoDTO dadosTransacao) {
        validarValor(dadosTransacao);
        validarSaldoEmpresa(dadosTransacao);

        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = recolherTaxa(dadosTransacao);
        BigDecimal valorTransacionado = (dadosTransacao.valor().add(taxaRecolhida)).negate();
        empresa.atualizarSaldo(valorTransacionado);
        salvarTransacao(dadosTransacao);
    }

    public void salvarTransacao(TransacaoDTO dadosTransacao) {
        Cliente cliente = clienteRepository.getReferenceById(dadosTransacao.idCliente());
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        Transacao transacao = new Transacao(cliente, empresa, dadosTransacao);
        transacaoRepository.save(transacao);
    }

}
