package com.example.votacionpresidencial.repositories;

import com.example.votacionpresidencial.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

}
