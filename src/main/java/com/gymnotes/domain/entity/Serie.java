package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // numero de series
    @Column(nullable = false)
    private Integer numeroDeSeries;

    // reps planejadas
    @Column(nullable = false)
    private Integer repeticoesPlanejadas;

    // peso planejado
    @Column(nullable = false)
    private Double pesoPlanejado;

    // exercício ao qual pertence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    // histórico de execuções dessa série
    @OneToMany(
            mappedBy = "serie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PesoFinal> pesosExecutados;
}
