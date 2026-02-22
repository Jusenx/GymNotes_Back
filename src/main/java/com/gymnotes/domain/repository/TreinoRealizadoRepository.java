package com.gymnotes.domain.repository;

import com.gymnotes.domain.entity.TreinoRealizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreinoRealizadoRepository extends JpaRepository<TreinoRealizado, Long> {
    List<TreinoRealizado> findByUsuarioId(Long usuario_id);

    @Query("""
    SELECT DISTINCT tr FROM TreinoRealizado tr
    JOIN FETCH tr.exercicios er
    JOIN FETCH er.series
""")
    List<TreinoRealizado> findAllWithExercicios();

    @Query("""
    SELECT DISTINCT tr FROM TreinoRealizado tr
    JOIN FETCH tr.exercicios er
    JOIN FETCH er.series
    WHERE tr.usuario.id = :usuarioId
""")
    List<TreinoRealizado> findByUsuarioIdWithExercicios(Long usuarioId);


    @Query("""
    SELECT tr FROM TreinoRealizado tr
    JOIN FETCH tr.exercicios er
    JOIN FETCH er.series
    WHERE tr.id = :id
""")
    Optional<TreinoRealizado> findByIdWithExercicios(@Param("id") Long id);

}
