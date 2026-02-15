package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TreinoResponseDTO {

    private Long id;
    private String nomeTreino;
    private List<ExercicioResponseDTO> exercicios;

    public TreinoResponseDTO(Long id, String nomeTreino, List<ExercicioResponseDTO> exercicios) {
        this.id = id;
        this.nomeTreino = nomeTreino;
        this.exercicios = exercicios;
    }
}
