package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "treinos_realizados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreinoRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate diaTreino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @OneToMany(
            mappedBy = "treinoRealizado",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExercicioRealizado> exercicios = new ArrayList<>();
}
