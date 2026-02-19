package com.gymnotes.domain.controller;

import com.gymnotes.domain.dto.response.TreinoRealizadoResponseDTO;
import com.gymnotes.domain.service.TreinoRealizadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos-realizados")
public class TreinoRealizadoController {
    TreinoRealizadoService service;

    public TreinoRealizadoController(TreinoRealizadoService service) {
        this.service = service;
    }

    @PostMapping("/iniciar/{treinoId}/{usuarioId}")
    public void iniciarTreino(@PathVariable Long treinoId, @PathVariable Long usuarioId){
        service.iniciarTreino(treinoId, usuarioId);
    }

    @GetMapping
    public List<TreinoRealizadoResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{treinoRealizadoId}")
    public TreinoRealizadoResponseDTO procurarPorId(@PathVariable Long treinoRealizadoId){
        return service.procurarPorId(treinoRealizadoId);
    }

    @GetMapping("/porUsuarioId/{usuarioId}")
    public List<TreinoRealizadoResponseDTO> procurarPorUsuarioId(@PathVariable Long usuarioId){
        return service.procurarPorUsuarioId(usuarioId);
    }
}
