package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoFinalResponseDTO {
    private Double peso;
    private Integer repeticoesRealizadas;

    public PesoFinalResponseDTO(Double peso, Integer repeticoesRealizadas) {
        this.peso = peso;
        this.repeticoesRealizadas = repeticoesRealizadas;
    }
}
