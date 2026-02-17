package com.gymnotes.domain.controller;

import com.gymnotes.domain.dto.request.TreinoRequestDTO;
import com.gymnotes.domain.dto.response.TreinoResponseDTO;
import com.gymnotes.domain.entity.Treino;
import com.gymnotes.domain.service.TreinoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/treinos")
public class TreinoController {
    private TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping("/{usuarioId}")
    public void criar(@PathVariable Long usuarioId, @RequestBody TreinoRequestDTO treino){
        treinoService.criarTreino(usuarioId, treino);
    }

    @GetMapping
    public List<TreinoResponseDTO> listar(){
        return treinoService.listar();
    }

    @GetMapping("/{treinoId}")
    public TreinoResponseDTO listarPorTreinoId(@PathVariable Long treinoId){
        return treinoService.procurarPorId(treinoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<TreinoResponseDTO> listarPorUsuarioId(@PathVariable Long usuarioId){
        return treinoService.procurarPorUsuarioId(usuarioId);
    }

    @PutMapping("/{treinoId}/{usuarioId}")
    public void alterar(@PathVariable Long treinoId, @PathVariable Long usuarioId, @RequestBody TreinoRequestDTO treino){
        treinoService.alterar(treinoId, usuarioId, treino);
    }

    @DeleteMapping("/{treinoId}")
    public void deletar(@PathVariable Long treinoId){
        treinoService.deletar(treinoId);
    }
}
