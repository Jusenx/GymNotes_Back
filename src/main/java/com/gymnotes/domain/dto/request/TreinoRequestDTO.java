package com.gymnotes.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreinoRequestDTO {
    private String nomeTreino;
    private List<ExercicioRequestDTO> exercicios = new ArrayList<>();
}
