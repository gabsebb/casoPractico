package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
        public boolean existsByCedula(String cedula);

    @Query("SELECT p FROM Persona p " +
            "LEFT JOIN FETCH p.ciudad c " +
            "LEFT JOIN FETCH c.provincia " +
            "LEFT JOIN FETCH p.genero " +
            "LEFT JOIN FETCH p.usuario")
    List<Persona> findAllWithRelations();
}
