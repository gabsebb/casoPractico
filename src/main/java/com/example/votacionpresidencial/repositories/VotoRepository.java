package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    public boolean existsByVotanteId(Long personaId);
}
