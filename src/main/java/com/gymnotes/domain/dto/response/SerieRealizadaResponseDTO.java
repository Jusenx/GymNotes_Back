package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieRealizadaResponseDTO {
    private Integer numero;
    private Integer repeticoesPlanejadas;
    private Integer repeticoesFeitas;
    private Double pesoPlanejado;
    private Double pesoUsado;

    public SerieRealizadaResponseDTO(Integer numero, Integer repeticoesPlanejadas, Integer repeticoesFeitas, Double pesoPlanejado, Double pesoUsado) {
        this.numero = numero;
        this.repeticoesPlanejadas = repeticoesPlanejadas;
        this.repeticoesFeitas = repeticoesFeitas;
        this.pesoPlanejado = pesoPlanejado;
        this.pesoUsado = pesoUsado;
    }
}
