package com.gymnotes.domain.dto;

import com.gymnotes.domain.entity.Exercicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreinoResponseDTO {


    private String nomeTreino;
    private List<Exercicio> exercicios = new ArrayList<>();

    public TreinoResponseDTO(String nomeTreino, List<Exercicio> exercicios) {
        this.nomeTreino = nomeTreino;
        this.exercicios = exercicios;
    }
}
