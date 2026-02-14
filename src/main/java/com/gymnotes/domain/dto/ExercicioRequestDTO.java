package com.gymnotes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExercicioRequestDTO {
    private String nome;
    private List<SerieRequestDTO> series;
}
