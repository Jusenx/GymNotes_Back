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

    // ordem da série (1ª, 2ª, 3ª...)
    @Column(nullable = false)
    private Integer numero;

    // reps planejadas
    @Column(nullable = false)
    private Integer repeticoesPlanejadas;

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
