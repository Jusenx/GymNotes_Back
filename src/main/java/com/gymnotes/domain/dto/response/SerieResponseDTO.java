package com.gymnotes.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SerieResponseDTO {
    private Integer numero;
    private Integer repeticoesPlanejadas;

    private List<PesoFinalResponseDTO> pesosExecutados;

    public SerieResponseDTO(Integer numero, Integer repeticoesPlanejadas, List<PesoFinalResponseDTO> pesosExecutados) {
        this.numero = numero;
        this.repeticoesPlanejadas = repeticoesPlanejadas;
        this.pesosExecutados = pesosExecutados;
    }
}
