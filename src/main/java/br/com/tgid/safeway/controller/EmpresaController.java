package br.com.tgid.safeway.controller;

import br.com.tgid.safeway.domain.empresa.EmpresaDTO;
import br.com.tgid.safeway.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmpresaDTO> cadastrar(@RequestBody EmpresaDTO dadosEmpresa) {
        empresaService.validarRequisicao(dadosEmpresa);
        empresaService.cadastrarEmpresa(dadosEmpresa);

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosEmpresa);
    }

}
