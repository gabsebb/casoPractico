package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.rol WHERE u.username = :username")
    Optional<Usuario> findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = :nombreRol")
    List<Usuario> findByRolNombre(String nombreRol);

    @Query("SELECT u FROM Usuario u WHERE u.persona IS NOT NULL")
    List<Usuario> findAllVotantes();

    @Query("SELECT u FROM Usuario u WHERE u.persona IS NULL")
    List<Usuario> findAllAdmins();

}
