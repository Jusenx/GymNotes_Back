package com.gymnotes.domain.service;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.Treino;
import com.gymnotes.domain.entity.Usuario;
import com.gymnotes.domain.mapper.TreinoMapper;
import com.gymnotes.domain.repository.TreinoRepository;
import com.gymnotes.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {
    private TreinoRepository treinoRepository;
    private TreinoMapper treinoMapper;
    private UsuarioRepository usuarioRepository;

    public TreinoService(TreinoRepository treinoRepository, UsuarioRepository usuarioRepository, TreinoMapper treinoMapper) {
        this.treinoRepository = treinoRepository;
        this.usuarioRepository = usuarioRepository;
        this.treinoMapper = treinoMapper;
    }

    public void criarTreino(Long usuarioId, TreinoRequestDTO dto) {

        if (dto.getNomeTreino() == null || dto.getNomeTreino().isBlank()) {
            throw new IllegalArgumentException("Nome do treino não pode ser vazio");

        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Treino treino = treinoMapper.toRequestDTO(dto, usuario);

        treinoRepository.save(treino);
    }

    public List<TreinoResponseDTO> listar() {
        List<Treino> treinos = treinoRepository.findAll();
        return treinoMapper.toResponseListDTO(treinos);
    }

    public TreinoResponseDTO procurarPorId(Long id) {

        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        return treinoMapper.toResponseDTO(treino);
    }

    public List<TreinoResponseDTO> procurarPorUsuarioId(Long id) {

        List<Treino> treinos = treinoRepository.findByUsuarioId(id);

        return treinoMapper.toResponseListDTO(treinos);
    }

    public void deletar(Long id) {
        treinoRepository.deleteById(id);
    }

    public void alterar(Long treinoId, Long usuarioId, TreinoRequestDTO dto){

        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        treino.setNomeTreino(dto.getNomeTreino());
        treino.setUsuario(usuario);

        treinoMapper.updateExercicios(treino, dto);

        treinoRepository.save(treino);
    }


}
