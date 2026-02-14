package com.gymnotes.domain.repository;

import com.gymnotes.domain.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
