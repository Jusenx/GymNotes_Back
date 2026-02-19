package com.gymnotes.domain.repository;

import com.gymnotes.domain.entity.TreinoRealizado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoRealizadoRepository extends JpaRepository<TreinoRealizado, Long> {
    List<TreinoRealizado> findByUsuarioId(Long usuario_id);
}
