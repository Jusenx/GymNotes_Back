package com.gymnotes.domain.mapper;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.ExercicioResponseDTO;
import com.gymnotes.domain.dto.response.PesoFinalResponseDTO;
import com.gymnotes.domain.dto.response.SerieResponseDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class TreinoMapper {

    public Treino toRequestDTO(TreinoRequestDTO dto, Usuario usuario) {
            Treino treino = new Treino();
            treino.setNomeTreino(dto.getNomeTreino());
            treino.setUsuario(usuario);
            List<Exercicio> exercicios = dto.getExercicios()
                    .stream()
                    .map(exDTO -> {
                        Exercicio exercicio = new Exercicio();
                        exercicio.setNome(exDTO.getNome());
                        exercicio.setTreino(treino);

                        Serie serie = new Serie();
                        serie.setNumeroDeSeries(exDTO.getNumeroDeSeries());
                        serie.setRepeticoesPlanejadas(exDTO.getRepeticoesPlanejadas());
                        serie.setPesoPlanejado(exDTO.getPesoPlanejado());
                        serie.setExercicio(exercicio);

                        exercicio.setSeries(List.of(serie));

                        return exercicio;
                    })
                    .toList();
        treino.setExercicios(exercicios);
        return treino;
    }

    public TreinoResponseDTO toResponseDTO(Treino treino) {

        return new TreinoResponseDTO(
                treino.getId(),
                treino.getNomeTreino(),
                treino.getExercicios()
                        .stream()
                        .map(this::toExercicioDTO)
                        .toList()
        );
    }

    public List<TreinoResponseDTO> toResponseListDTO(List<Treino> treinos) {

        return treinos
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    private ExercicioResponseDTO toExercicioDTO(Exercicio exercicio) {

        return new ExercicioResponseDTO(
                exercicio.getNome(),
                exercicio.getSeries()
                        .stream()
                        .map(this::toSerieDTO)
                        .toList()
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
                        .toList()
        );
    }

    private PesoFinalResponseDTO toPesoFinalDTO(PesoFinal peso) {

        return new PesoFinalResponseDTO(
                peso.getPeso(),
                peso.getRepeticoesRealizadas()
        );
    }
}
