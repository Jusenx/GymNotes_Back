package com.gymnotes.domain.controller;

import com.gymnotes.domain.dto.UsuarioRequestDTO;
import com.gymnotes.domain.entity.Usuario;
import com.gymnotes.domain.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario criar(@RequestBody UsuarioRequestDTO usuario){
        return usuarioService.criar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.exluir(id);
    }
}
