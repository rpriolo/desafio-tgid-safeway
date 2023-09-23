package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.empresa.Empresa;
import br.com.tgid.safeway.domain.empresa.EmpresaDTO;
import br.com.tgid.safeway.exception.*;
import br.com.tgid.safeway.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Classe responsável por processar as lógicas de negócio a partir dos dados recebidos no controller de empresas.
 */
@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Recebe as informações da requisição HTTP e cadastra uma empresa no banco de dados.
     *
     * @param dadosEmpresa O conjunto de dados que descrevem e compõem uma empresa.
     */
    public void cadastrarEmpresa(EmpresaDTO dadosEmpresa) {
        Empresa empresa = new Empresa(dadosEmpresa);
        empresaRepository.save(empresa);
    }

    /**
     * Recebe as informações da requisição HTTP e faz as validações necessárias para o cadastro de empresas.
     *
     * @param dadosEmpresa O conjunto de dados que descrevem e compõem uma empresa.
     */
    public void validarRequisicao(EmpresaDTO dadosEmpresa) {
        if (empresaRepository.existsByCnpj(dadosEmpresa.cnpj())) {
            throw new EmpresaExistenteException("O CNPJ informado já está cadastrado na base de dados");
        }
        if (dadosEmpresa.cnpj() == null || dadosEmpresa.saldo() == null || dadosEmpresa.taxaAdministracao() == null) {
            throw new DadoNaoInformadoEmpresaException("É necessário informar CNPJ, saldo e taxa de administração para cadastrar uma empresa");
        }
        if (dadosEmpresa.cnpj().length() != 14) {
            throw new CnpjInvalidoException("O CNPJ deve ter 14 dígitos");
        }
        if (dadosEmpresa.saldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInvalidoException("O saldo deve ser maior ou igual a zero");
        }
        if (dadosEmpresa.taxaAdministracao().compareTo(BigDecimal.ZERO) < 0) {
            throw new TaxaInvalidaException("A taxa de administração deve ser maior ou igual a zero");
        }
    }

}
