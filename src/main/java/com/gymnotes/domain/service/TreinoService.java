package com.gymnotes.domain.service;

import com.gymnotes.domain.dto.TreinoRequestDTO;
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

    public void criarTreino(Long id, TreinoRequestDTO dto) {

        if(dto.getNomeTreino() == null || dto.getNomeTreino().isBlank()) {
            throw new IllegalArgumentException("Nome do treino não pode ser vazio");

        }
        System.out.println(dto.getNomeTreino());


        Treino treino = new Treino();
        treino.setNome(dto.getNomeTreino()); // garante que não seja nulo

        // buscar usuário
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        treino.setUsuario(usuario);

        // mapear exercícios e séries...
        List<Exercicio> exercicios = dto.getExercicios()
                .stream()
                .map(exDto -> {
                    Exercicio exercicio = new Exercicio();
                    exercicio.setNome(exDto.getNome());
                    exercicio.setTreino(treino);

                    List<Serie> series = exDto.getSeries()
                            .stream()
                            .map(sDto -> {
                                Serie serie = new Serie();
                                serie.setNumero(sDto.getNumero());
                                serie.setRepeticoesPlanejadas(sDto.getRepeticoesPlanejadas());
                                serie.setExercicio(exercicio);
                                return serie;
                            })
                            .toList();

                    exercicio.setSeries(series);

                    return exercicio;
                })
                .toList();

        treino.setExercicios(exercicios);

        treinoRepository.save(treino); // salva tudo
    }


}
