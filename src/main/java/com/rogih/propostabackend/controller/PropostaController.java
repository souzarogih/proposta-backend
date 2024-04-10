package com.rogih.propostabackend.controller;

import com.rogih.propostabackend.dto.PropostaRequestDto;
import com.rogih.propostabackend.dto.PropostaResponseDto;
import com.rogih.propostabackend.service.PropostaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto) {
        log.info("Controller para criar proposta.");
        PropostaResponseDto responseDto = propostaService.criar(propostaRequestDto);
        log.info("Respondendo a proposta {}", responseDto.getCpf());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> obter() {
        log.info("Controller que lista todas as propostas");
        return ResponseEntity.ok(propostaService.obterProposta());
    }
}
