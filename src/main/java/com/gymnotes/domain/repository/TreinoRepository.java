package com.gymnotes.domain.repository;

import com.gymnotes.domain.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

        List<Treino> findByUsuarioId(Long usuario_id);
}
