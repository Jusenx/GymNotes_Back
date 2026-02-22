package com.gymnotes.domain.service;

import com.gymnotes.domain.dto.response.TreinoRealizadoResponseDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.*;
import com.gymnotes.domain.mapper.TreinoRealizadoMapper;
import com.gymnotes.domain.repository.TreinoRealizadoRepository;
import com.gymnotes.domain.repository.TreinoRepository;
import com.gymnotes.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreinoRealizadoService {
    TreinoRealizadoRepository treinoRealizadoRepository;
    TreinoRepository treinoRepository;
    UsuarioRepository usuarioRepository;
    TreinoRealizadoMapper treinoRealizadoMapper;

    public TreinoRealizadoService(TreinoRealizadoRepository treinoRealizadoRepository, TreinoRepository treinoRepository, UsuarioRepository usuarioRepository, TreinoRealizadoMapper treinoRealizadoMapper) {
        this.treinoRealizadoRepository = treinoRealizadoRepository;
        this.treinoRepository = treinoRepository;
        this.usuarioRepository = usuarioRepository;
        this.treinoRealizadoMapper = treinoRealizadoMapper;
    }

    public void iniciarTreino(Long treinoId, Long usuarioId) {

        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow();
        TreinoRealizado treinoRealizado = new TreinoRealizado();
        treinoRealizado.setTreino(treino);
        treinoRealizado.setUsuario(usuario);
        treinoRealizado.setDiaTreino(LocalDate.now());

        treinoRealizado.setExercicios(new ArrayList<>());

        for (Exercicio exercicio : treino.getExercicios()) {

            ExercicioRealizado er = new ExercicioRealizado();
            er.setNome(exercicio.getNome());
            er.setTreinoRealizado(treinoRealizado);
            er.setSeries(new ArrayList<>());

            treinoRealizado.getExercicios().add(er);

            for (Serie serie : exercicio.getSeries()) {

                SerieRealizada sr = new SerieRealizada();

                sr.setNumero(serie.getNumeroDeSeries());
                sr.setPesoPlanejado(serie.getPesoPlanejado());
                sr.setRepeticoesPlanejadas(
                        serie.getRepeticoesPlanejadas()
                );

                sr.setExercicioRealizado(er);

                er.getSeries().add(sr);
            }
        }

        treinoRealizadoRepository.save(treinoRealizado);
    }

    public List<TreinoRealizadoResponseDTO> listar(){
        List<TreinoRealizado> treinos = treinoRealizadoRepository.findAll();
        return treinoRealizadoMapper.toResponseListDTO(treinos);
    }

    public TreinoRealizadoResponseDTO procurarPorId(Long id) {

        TreinoRealizado treino = treinoRealizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        return treinoRealizadoMapper.toResponseDTO(treino);
    }

    public List<TreinoRealizadoResponseDTO> procurarPorUsuarioId(Long id) {

        List<TreinoRealizado> treinos = treinoRealizadoRepository.findByUsuarioId(id);

        return treinoRealizadoMapper.toResponseListDTO(treinos);
    }

    public void deletar(Long id) {
        treinoRealizadoRepository.deleteById(id);
    }


}
