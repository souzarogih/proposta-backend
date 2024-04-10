package com.rogih.propostabackend.agendador;

import com.rogih.propostabackend.entity.Proposta;
import com.rogih.propostabackend.repository.PropostaRepository;
import com.rogih.propostabackend.service.NotificationRabbitService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private NotificationRabbitService notificationRabbitService;

    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao() {
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                logger.info("proposta: {}", proposta);
                logger.info("exchange: {}", exchange);
                notificationRabbitService.notificar(proposta, exchange);
                atualizarProposta(proposta);
                logger.info("Proposta integrada com sucesso | {}", proposta.getId());
            }catch (RuntimeException ex) {
                logger.error("Retentativa de integrar a proposta {} falhou.", proposta.getId());
                logger.error("Ocorreu um erro em nova tentativa de notificar a proposta. {}", ex.getMessage());
                logger.warn("Verifique se o serviço de fila está up.");
            }
        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
