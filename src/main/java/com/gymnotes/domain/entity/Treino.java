package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "treinos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTreino;

    // muitos treinos → um usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // um treino → muitos exercícios
    @OneToMany(
            mappedBy = "treino",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Exercicio> exercicios;
}
