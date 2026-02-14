package com.gymnotes.domain.controller;

import com.gymnotes.domain.dto.TreinoRequestDTO;
import com.gymnotes.domain.entity.Treino;
import com.gymnotes.domain.service.TreinoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treinos")
public class TreinoController {
    private TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping("/{id}")
    public void criar(@PathVariable Long id, TreinoRequestDTO treino){
        treinoService.criarTreino(id, treino);
    }
}
