package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TreinoRealizadoResponseDTO {
    private Long id;
    private LocalDate diaTreino;
    private List<ExercicioRealizadoResponseDTO> exercicios;

    public TreinoRealizadoResponseDTO(Long id, LocalDate diaTreino, List<ExercicioRealizadoResponseDTO> exercicios) {
        this.id = id;
        this.diaTreino = diaTreino;
        this.exercicios = exercicios;
    }
}
