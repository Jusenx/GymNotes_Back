package com.gymnotes.domain.service;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.ExercicioResponseDTO;
import com.gymnotes.domain.dto.response.PesoFinalResponseDTO;
import com.gymnotes.domain.dto.response.SerieResponseDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.Exercicio;
import com.gymnotes.domain.entity.Serie;
import com.gymnotes.domain.entity.Treino;
import com.gymnotes.domain.entity.Usuario;
import com.gymnotes.domain.repository.TreinoRepository;
import com.gymnotes.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {
    private TreinoRepository treinoRepository;
    private UsuarioRepository usuarioRepository;

    public TreinoService(TreinoRepository treinoRepository, UsuarioRepository usuarioRepository) {
        this.treinoRepository = treinoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void criarTreino(Long usuarioId, TreinoRequestDTO dto) {

        if(dto.getNomeTreino() == null || dto.getNomeTreino().isBlank()) {
            throw new IllegalArgumentException("Nome do treino não pode ser vazio");

        }


        Treino treino = new Treino();
        treino.setNomeTreino(dto.getNomeTreino());

        // buscar usuário
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        treino.setUsuario(usuario);

        // mapear exercícios e séries...
        List<Exercicio> exercicios = dto.getExercicios()
                .stream()
                .map(exDto -> {

                    Exercicio exercicio = new Exercicio();
                    exercicio.setNome(exDto.getNome());
                    exercicio.setTreino(treino);

                    // cria a série
                    Serie serie = new Serie();
                    serie.setNumeroDeSeries(exDto.getNumeroDeSeries());
                    serie.setRepeticoesPlanejadas(exDto.getRepeticoesPlanejadas());
                    serie.setPesoPlanejado(exDto.getPesoPlanejado());
                    serie.setExercicio(exercicio);

                    exercicio.setSeries(List.of(serie));

                    return exercicio;
                })
                .toList();

        treino.setExercicios(exercicios);


        treinoRepository.save(treino); // salva tudo
    }

    public List<TreinoResponseDTO> listar() {
        return treinoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    private TreinoResponseDTO converterParaDTO(Treino treino) {

        List<ExercicioResponseDTO> exercicios =
                treino.getExercicios()
                        .stream()
                        .map(ex -> new ExercicioResponseDTO(
                                ex.getNome(),
                                ex.getSeries()
                                        .stream()
                                        .map(s -> new SerieResponseDTO(
                                                s.getNumeroDeSeries(),
                                                s.getRepeticoesPlanejadas(),
                                                s.getPesosExecutados()
                                                        .stream()
                                                        .map(p -> new PesoFinalResponseDTO(
                                                                p.getPeso(),
                                                                p.getRepeticoesRealizadas()
                                                        ))
                                                        .toList()
                                        ))
                                        .toList()
                        ))
                        .toList();

        return new TreinoResponseDTO(
                treino.getNomeTreino(),
                exercicios
        );
    }



}
