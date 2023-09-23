package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.notificacao.NotificacaoDTO;
import br.com.tgid.safeway.domain.transacao.TipoTransacao;
import br.com.tgid.safeway.exception.ServicoForaDoArException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class NotificacaoService {

    @Autowired
    private RestTemplate restTemplate;

    public void enviarCallback(TipoTransacao tipo, BigDecimal valor) {
        String url = "https://webhook.site/9a5db045-1272-40d5-8e1b-83632b5992eb";
        NotificacaoDTO notificacao = new NotificacaoDTO("Uma transação foi efetivada em sua conta", tipo, valor);

        ResponseEntity<String> resposta = restTemplate.postForEntity(url, notificacao, String.class);

        if (resposta.getStatusCode().isError()) {
            throw new ServicoForaDoArException("O serviço de comunicação está fora do ar");
        }
    }

}
