package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TreinoResponseDTO {


    private String nomeTreino;
    private List<ExercicioResponseDTO> exercicios;

    public TreinoResponseDTO(String nomeTreino, List<ExercicioResponseDTO> exercicios) {
        this.nomeTreino = nomeTreino;
        this.exercicios = exercicios;
    }
}
