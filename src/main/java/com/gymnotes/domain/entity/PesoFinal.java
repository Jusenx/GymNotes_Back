package com.gymnotes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pesos_finais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PesoFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Integer repeticoesRealizadas;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    // muitos registros de peso → uma série
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;
}
