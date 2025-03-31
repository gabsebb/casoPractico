package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByPartidoId(Long partidoId);
}
