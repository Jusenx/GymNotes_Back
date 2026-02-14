package com.gymnotes.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesoFinalRequestDTO {

    private Long serieId;
    private Double peso;
    private Integer repeticoesRealizadas;
}
