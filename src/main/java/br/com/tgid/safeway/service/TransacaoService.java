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

/**
 * Classe responsável por processar as lógicas de negócio a partir dos dados recebidos no controller de transações.
 */
@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Recebe as informações da requisição HTTP e faz as validações necessárias para prosseguir
     * com uma transação de acordo com seu valor.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void validarValor(TransacaoDTO dadosTransacao) {
        if (dadosTransacao.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransacaoInvalidaException("O valor da transação precisa ser maior que zero");
        }
    }

    /**
     * Recebe as informações da requisição HTTP e faz as validações necessárias para prosseguir
     * com o processamento da transação de acordo com o seu tipo.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void validarTipoTransacao(TransacaoDTO dadosTransacao) {
        if (dadosTransacao.tipo() == TipoTransacao.DEPOSITO) {
            efetivarDeposito(dadosTransacao);
        } else if (dadosTransacao.tipo() == TipoTransacao.SAQUE) {
            efetivarSaque(dadosTransacao);
        }
    }

    /**
     * Recebe as informações da requisição HTTP e faz o cálculo para recolhimento da taxa de administração.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     * @return Retorna o montante em reais (R$) que será recolhido ao final da transação referente à taxa de administração.
     */
    public BigDecimal recolherTaxa(TransacaoDTO dadosTransacao) {
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = dadosTransacao.valor().multiply(empresa.getTaxaAdministracao());
        return taxaRecolhida;
    }

    /**
     * Recebe as informações da requisição HTTP e realiza a efetivação do depósito
     * e persistência da transação no banco de dados.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void efetivarDeposito(TransacaoDTO dadosTransacao) {
        validarValor(dadosTransacao);

        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = recolherTaxa(dadosTransacao);
        BigDecimal valorTransacionado = dadosTransacao.valor().subtract(taxaRecolhida);
        empresa.atualizarSaldo(valorTransacionado);
        salvarTransacao(dadosTransacao);
    }

    /**
     * Recebe as informações da requisição HTTP e valida se a empresa tem saldo suficiente
     * para pagar o saque solicitado por um cliente.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void validarSaldoEmpresa(TransacaoDTO dadosTransacao) {
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = dadosTransacao.valor().multiply(empresa.getTaxaAdministracao());
        BigDecimal valorTotalTransacao = dadosTransacao.valor().add(taxaRecolhida);

        if (empresa.getSaldo().compareTo(valorTotalTransacao) < 0) {
            throw new SaldoInsuficienteException("A empresa não tem saldo suficiente para efetivar essa transação");
        }
    }

    /**
     * Recebe as informações da requisição HTTP e realiza a efetivação do saque
     * e persistência da transação no banco de dados.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void efetivarSaque(TransacaoDTO dadosTransacao) {
        validarValor(dadosTransacao);
        validarSaldoEmpresa(dadosTransacao);

        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        BigDecimal taxaRecolhida = recolherTaxa(dadosTransacao);
        BigDecimal valorTransacionado = (dadosTransacao.valor().add(taxaRecolhida)).negate();
        empresa.atualizarSaldo(valorTransacionado);
        salvarTransacao(dadosTransacao);
    }

    /**
     * Persiste a transação no banco de dados.
     *
     * @param dadosTransacao O conjunto de dados que descrevem e compõem a transação.
     */
    public void salvarTransacao(TransacaoDTO dadosTransacao) {
        Cliente cliente = clienteRepository.getReferenceById(dadosTransacao.idCliente());
        Empresa empresa = empresaRepository.getReferenceById(dadosTransacao.idEmpresa());
        Transacao transacao = new Transacao(cliente, empresa, dadosTransacao);
        transacaoRepository.save(transacao);
    }

}
