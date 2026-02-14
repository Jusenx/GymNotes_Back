package com.gymnotes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExercicioResponseDTO {
    private String nome;
    private List<SerieResponseDTO> series;
}
