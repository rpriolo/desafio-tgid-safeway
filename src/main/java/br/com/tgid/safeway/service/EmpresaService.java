package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.empresa.Empresa;
import br.com.tgid.safeway.domain.empresa.EmpresaDTO;
import br.com.tgid.safeway.exception.CnpjInvalidoException;
import br.com.tgid.safeway.exception.DadoNaoInformadoEmpresaException;
import br.com.tgid.safeway.exception.SaldoInvalidoException;
import br.com.tgid.safeway.exception.TaxaInvalidaException;
import br.com.tgid.safeway.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private TransacaoService transacaoService;

    public void cadastrarEmpresa(EmpresaDTO dadosEmpresa) {
        Empresa empresa = new Empresa(dadosEmpresa);
        empresaRepository.save(empresa);
    }

    public void validarRequisicao(EmpresaDTO dadosEmpresa) {
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
