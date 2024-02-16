package com.rogih.propostabackend.controller;

import com.rogih.propostabackend.dto.PropostaRequestDto;
import com.rogih.propostabackend.dto.PropostaResponseDto;
import com.rogih.propostabackend.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto) {
        PropostaResponseDto responseDto = propostaService.criar(propostaRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
