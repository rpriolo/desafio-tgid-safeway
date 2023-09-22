package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.empresa.Empresa;
import br.com.tgid.safeway.domain.empresa.EmpresaDTO;
import br.com.tgid.safeway.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
