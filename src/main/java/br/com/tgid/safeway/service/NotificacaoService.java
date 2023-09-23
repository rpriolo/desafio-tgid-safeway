package br.com.tgid.safeway.service;

import br.com.tgid.safeway.domain.notificacao.NotificacaoDTO;
import br.com.tgid.safeway.domain.transacao.TipoTransacao;
import br.com.tgid.safeway.exception.ServicoForaDoArException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Classe responsável por processar e enviar as comunicações sobre transações às empresas e clientes.
 */
@Service
public class NotificacaoService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Envia uma notificação à empresa quando uma transação é realizada.
     *
     * @param tipo  O tipo da transação realizada, podendo ser depósito ou saque.
     * @param valor O montante em reais (R$) transacionado entre as partes.
     */
    public void enviarCallback(TipoTransacao tipo, BigDecimal valor) {
        String url = "https://webhook.site/9a5db045-1272-40d5-8e1b-83632b5992eb";
        NotificacaoDTO notificacao = new NotificacaoDTO("Uma transação foi efetivada em sua conta", tipo, valor);

        ResponseEntity<String> resposta = restTemplate.postForEntity(url, notificacao, String.class);

        if (resposta.getStatusCode().isError()) {
            throw new ServicoForaDoArException("O serviço de comunicação está fora do ar");
        }
    }

    /**
     * Envia um e-mail ao cliente quando uma transação é realizada.
     *
     * @param tipo  O tipo da transação realizada, podendo ser depósito ou saque.
     * @param valor O montante em reais (R$) transacionado entre as partes.
     */
    public void enviarEmail(TipoTransacao tipo, BigDecimal valor) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("9a5db045-1272-40d5-8e1b-83632b5992eb@email.webhook.site");
        email.setSubject("Transação realizada em sua conta");
        email.setText("Seu " + tipo + " de R$ " + valor + " foi efetivado com sucesso!");
        email.setFrom(System.getenv("JAVAMAILSENDER_EMAIL"));
        javaMailSender.send(email);
    }

}
