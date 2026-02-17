package com.gymnotes.domain.mapper;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.ExercicioResponseDTO;
import com.gymnotes.domain.dto.response.PesoFinalResponseDTO;
import com.gymnotes.domain.dto.response.SerieResponseDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TreinoMapper {

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
            serie.setPesoPlanejado(exDTO.getPesoPlanejado());
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
    public TreinoResponseDTO toResponseDTO(Treino treino) {

        return new TreinoResponseDTO(
                treino.getId(),
                treino.getNomeTreino(),
                treino.getExercicios()
                        .stream()
                        .map(this::toExercicioDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<TreinoResponseDTO> toResponseListDTO(List<Treino> treinos) {

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
            serie.setPesoPlanejado(exDTO.getPesoPlanejado());
            serie.setExercicio(exercicio);

            exercicio.getSeries().add(serie);

            // adiciona na MESMA lista
            treino.getExercicios().add(exercicio);
        });
    }


    // =========================
    // PRIVATE MAPPERS
    // =========================
    private ExercicioResponseDTO toExercicioDTO(Exercicio exercicio) {

        return new ExercicioResponseDTO(
                exercicio.getNome(),
                exercicio.getSeries()
                        .stream()
                        .map(this::toSerieDTO)
                        .collect(Collectors.toList())
        );
    }

    private SerieResponseDTO toSerieDTO(Serie serie) {

        return new SerieResponseDTO(
                serie.getNumeroDeSeries(),
                serie.getRepeticoesPlanejadas(),
                serie.getPesoPlanejado(),
                serie.getPesosExecutados()
                        .stream()
                        .map(this::toPesoFinalDTO)
                        .collect(Collectors.toList())
        );
    }

    private PesoFinalResponseDTO toPesoFinalDTO(PesoFinal peso) {

        return new PesoFinalResponseDTO(
                peso.getPeso(),
                peso.getRepeticoesRealizadas()
        );
    }
}
