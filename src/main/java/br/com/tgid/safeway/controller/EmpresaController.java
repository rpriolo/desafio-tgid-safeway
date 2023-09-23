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

/**
 * Classe responsável por gerenciar as requisições da API referentes às empresas.
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    /**
     * Valida e realiza o cadastro de uma empresa a partir dos dados fornecidos.
     *
     * @param dadosEmpresa O conjunto de dados que descrevem e compõem uma empresa.
     * @return Retorna um código de status HTTP e uma resposta com os dados do cadastro realizado.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<EmpresaDTO> cadastrar(@RequestBody EmpresaDTO dadosEmpresa) {
        empresaService.validarRequisicao(dadosEmpresa);
        empresaService.cadastrarEmpresa(dadosEmpresa);

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosEmpresa);
    }

}
