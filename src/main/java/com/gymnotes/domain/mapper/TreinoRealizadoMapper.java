package com.gymnotes.domain.mapper;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.*;
import com.gymnotes.domain.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TreinoRealizadoMapper {

    // =========================
    // CREATE
    // =========================
    public Treino toRequestDTO(TreinoRequestDTO dto, Usuario usuario) {

        Treino treino = new Treino();
        treino.setNomeTreino(dto.getNomeTreino());
        treino.setUsuario(usuario);

        List<Exercicio> exercicios = new ArrayList<>();

        dto.getExercicios().forEach(exDTO -> {

            Exercicio exercicio = new Exercicio();
            exercicio.setNome(exDTO.getNome());
            exercicio.setTreino(treino);

            Serie serie = new Serie();
            serie.setNumeroDeSeries(exDTO.getNumeroDeSeries());
            serie.setRepeticoesPlanejadas(exDTO.getRepeticoesPlanejadas());
            serie.setExercicio(exercicio);

            List<Serie> series = new ArrayList<>();
            series.add(serie);

            exercicio.setSeries(series);

            exercicios.add(exercicio);
        });

        treino.setExercicios(exercicios);

        return treino;
    }

    // =========================
    // RESPONSE
    // =========================
    public TreinoRealizadoResponseDTO toResponseDTO(TreinoRealizado treinoRealizado) {

        return new TreinoRealizadoResponseDTO(
                treinoRealizado.getId(),
                treinoRealizado.getDiaTreino(),
                treinoRealizado.getExercicios()
                        .stream()
                        .map(this::toExercicioDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<TreinoRealizadoResponseDTO> toResponseListDTO(List<TreinoRealizado> treinos) {

        return treinos
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // UPDATE (ALTERAR)
    // =========================
    public void updateExercicios(Treino treino, TreinoRequestDTO dto) {

        // remove os antigos (Hibernate deleta automaticamente)
        treino.getExercicios().clear();

        dto.getExercicios().forEach(exDTO -> {

            Exercicio exercicio = new Exercicio();
            exercicio.setNome(exDTO.getNome());
            exercicio.setTreino(treino);

            Serie serie = new Serie();
            serie.setNumeroDeSeries(exDTO.getNumeroDeSeries());
            serie.setRepeticoesPlanejadas(exDTO.getRepeticoesPlanejadas());
            serie.setExercicio(exercicio);

            exercicio.getSeries().add(serie);

            // adiciona na MESMA lista
            treino.getExercicios().add(exercicio);
        });
    }


    // =========================
    // PRIVATE MAPPERS
    // =========================
    private ExercicioRealizadoResponseDTO toExercicioDTO(ExercicioRealizado exercicioRealizado) {

        System.out.println(
                exercicioRealizado.getSeries().get(0).getNumero()
        );

        return new ExercicioRealizadoResponseDTO(
                exercicioRealizado.getNome(),
                exercicioRealizado.getSeries()
                        .stream()
                        .map(this::toSerieDTO)
                        .collect(Collectors.toList())

        );
    }

    private SerieRealizadaResponseDTO toSerieDTO(SerieRealizada serieRealizada) {

        return new SerieRealizadaResponseDTO(
                serieRealizada.getNumero(),
                serieRealizada.getRepeticoesPlanejadas(),
                serieRealizada.getRepeticoesFeitas(),
                serieRealizada.getPesoPlanejado(),
                serieRealizada.getPesoUsado()
        );
    }

}
