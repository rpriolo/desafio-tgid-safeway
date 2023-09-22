package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.empresa.EmpresaDTO;
import br.com.tgid.safeway.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody EmpresaDTO dadosEmpresa) {
    }

}
