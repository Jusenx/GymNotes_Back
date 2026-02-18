package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercicios_realizados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private TreinoRealizado treinoRealizado;

    @OneToMany(mappedBy = "exercicioRealizado",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SerieRealizada> series = new ArrayList<>();
}

