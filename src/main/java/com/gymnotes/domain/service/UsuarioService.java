package com.gymnotes.domain.service;

import com.gymnotes.domain.dto.request.UsuarioRequestDTO;
import com.gymnotes.domain.entity.Usuario;
import com.gymnotes.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criar(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha()
        );
        return repository.save(usuario);
    }

    public void exluir(Long id){
        repository.deleteById(id);
    }
}
