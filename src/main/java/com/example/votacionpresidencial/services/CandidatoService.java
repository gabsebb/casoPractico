package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.Candidato;
import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.repositories.CandidatoRepository;
import com.example.votacionpresidencial.repositories.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatoService {
    private final CandidatoRepository candidatoRepository;
    private final PartidoRepository partidoRepository;

    public Candidato crearCandidato(Candidato candidato, Long partidoId) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado"));
        candidato.setPartido(partido);
        return candidatoRepository.save(candidato);
    }

    public List<Candidato> listarCandidatosPorPartido(Long partidoId) {
        return candidatoRepository.findByPartidoId(partidoId);
    }
}
