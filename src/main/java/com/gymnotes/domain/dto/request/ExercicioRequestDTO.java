package com.gymnotes.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercicioRequestDTO {
    private String nome;
    private Integer numeroDeSeries;
    private Integer repeticoesPlanejadas;
    private Double pesoPlanejado;
}
