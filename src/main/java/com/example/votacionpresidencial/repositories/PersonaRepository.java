package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
        public boolean existsByCedula(String cedula);

    @Query("SELECT p FROM Persona p " +
            "LEFT JOIN FETCH p.ciudad c " +
            "LEFT JOIN FETCH c.provincia " +
            "LEFT JOIN FETCH p.genero " +
            "LEFT JOIN FETCH p.usuario")
    List<Persona> findAllWithRelations();

    @Query("SELECT p FROM Persona p " +
            "LEFT JOIN FETCH p.ciudad c " +
            "LEFT JOIN FETCH p.genero " +
            "LEFT JOIN FETCH p.usuario WHERE p.id = :id")
    Optional<Persona> findByIdWithRelations(@Param("id") Long id);
}
