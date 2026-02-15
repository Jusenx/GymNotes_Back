package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExercicioResponseDTO {
    private String nome;
    private List<SerieResponseDTO> series;

    public ExercicioResponseDTO(String nome, List<SerieResponseDTO> series) {
        this.nome = nome;
        this.series = series;

    }
}
