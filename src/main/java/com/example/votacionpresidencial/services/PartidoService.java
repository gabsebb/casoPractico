package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.repositories.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidoService {
    private final PartidoRepository partidoRepository;

    public Partido crearPartido(Partido partido) {
        if (partidoRepository.existsByNombre(partido.getNombre())) {
            throw new IllegalStateException("Ya existe un partido con ese nombre");
        }
        return partidoRepository.save(partido);
    }

    public List<Partido> listarPartidos() {
        return partidoRepository.findAll();
    }

    public Partido obtenerPartidoConCandidatos(Long id) {
        return partidoRepository.findByIdWithCandidatos(id)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado"));
    }

    public List<Partido> listarPartidosConCandidatos() {
        return partidoRepository.findAllWithCandidatos();
    }
}
