package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.repositories.CiudadRepository;
import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Object listarTodas() {
        return ciudadRepository.findAll();
    }
}
