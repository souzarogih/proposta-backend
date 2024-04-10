package com.rogih.propostabackend.service;

import com.rogih.propostabackend.dto.PropostaRequestDto;
import com.rogih.propostabackend.dto.PropostaResponseDto;
import com.rogih.propostabackend.entity.Proposta;
import com.rogih.propostabackend.mapper.PropostaMapper;
import com.rogih.propostabackend.repository.PropostaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private NotificationRabbitService notificationService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;


    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        log.info("Criando proposta {}", requestDto.getCpf());
        log.debug("Exchange {} carregada com sucesso.", exchange);

        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        try {
            notificationService.notificar(proposta, exchange);
        } catch (RuntimeException ex){
            log.warn("Ocorreu um erro ao notificar a proposta {} usuario: {}", proposta.getId(), proposta.getUsuario() );
            log.error("Erro ao notificar a proposta: {}", ex.getMessage());
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> obterProposta() {
        log.info("Localizando todas as propostas");
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
