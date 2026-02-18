package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "series_realizadas")
public class SerieRealizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private Integer repeticoesPlanejadas;

    private Integer repeticoesFeitas;

    private Double pesoPlanejado;

    private Double pesoUsado;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExercicioRealizado exercicioRealizado;
}
