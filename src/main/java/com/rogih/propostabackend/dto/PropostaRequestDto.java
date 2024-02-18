package com.rogih.propostabackend.dto;

import com.rogih.propostabackend.entity.Usuario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaRequestDto {

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Usuario usuario;
}
