package com.rogih.propostabackend.service;

import com.rogih.propostabackend.entity.Proposta;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class NotificationService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange) {
        log.info("Preparando o envio da notificação para exchange: {}", exchange);
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
