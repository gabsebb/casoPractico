package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.repositories.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Object listarTodos() {
        return generoRepository.findAll();
    }
}
