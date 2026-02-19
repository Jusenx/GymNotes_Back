package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExercicioRealizadoResponseDTO {
    private String nome;
    private List<SerieRealizadaResponseDTO> series;

    public ExercicioRealizadoResponseDTO(String nome, List<SerieRealizadaResponseDTO> series) {
        this.nome = nome;
        this.series = series;
    }
}
