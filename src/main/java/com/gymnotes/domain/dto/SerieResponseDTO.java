package com.gymnotes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SerieResponseDTO {
    private Integer numero;
    private Integer repeticoesPlanejadas;

    private List<PesoFinalResponseDTO> pesosExecutados = new ArrayList<>();
}
