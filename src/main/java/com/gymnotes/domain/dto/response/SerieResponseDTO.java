package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SerieResponseDTO {
    private Integer numero;
    private Integer repeticoesPlanejadas;
    private Double pesoPlanejado;


    public SerieResponseDTO(Integer numero, Integer repeticoesPlanejadas, Double pesoPlanejado) {
        this.numero = numero;
        this.repeticoesPlanejadas = repeticoesPlanejadas;
        this.pesoPlanejado = pesoPlanejado;
    }
}
