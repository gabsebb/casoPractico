package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    boolean existsByNombre(String nombre);
    @Query("SELECT p FROM Partido p LEFT JOIN FETCH p.candidatos WHERE p.id = :id")
    Optional<Partido> findByIdWithCandidatos(@Param("id") Long id);

    @Query("SELECT p FROM Partido p LEFT JOIN FETCH p.candidatos")
    List<Partido> findAllWithCandidatos();
}
