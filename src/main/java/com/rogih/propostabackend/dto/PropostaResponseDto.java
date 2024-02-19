package com.rogih.propostabackend.dto;

import com.rogih.propostabackend.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDto {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

    private String valorSolicitadoFmt;

    private int prazoPagamento;

    private boolean integrada;

    private Boolean aprovada;

    private String observacao;

}
