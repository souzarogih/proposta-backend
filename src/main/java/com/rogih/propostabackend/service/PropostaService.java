package com.rogih.propostabackend.service;

import com.rogih.propostabackend.dto.PropostaRequestDto;
import com.rogih.propostabackend.dto.PropostaResponseDto;
import com.rogih.propostabackend.entity.Proposta;
import com.rogih.propostabackend.mapper.PropostaMapper;
import com.rogih.propostabackend.repository.PropostaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    public PropostaResponseDto criar(PropostaRequestDto requestDto){
        log.info("Criando proposta {}", requestDto.getCpf());
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }
}
