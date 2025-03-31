package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.models.Voto;
import com.example.votacionpresidencial.repositories.PartidoRepository;
import com.example.votacionpresidencial.repositories.PersonaRepository;
import com.example.votacionpresidencial.repositories.VotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class VotoService {
    private final VotoRepository votoRepository;
    private final PersonaRepository personaRepository;
    private final PartidoRepository partidoRepository;

    @Transactional
    public Voto registrarVoto(Long personaId, Long partidoId) {
        validarHorarioVotacion();

        if (votoRepository.existsByVotanteId(personaId)) {
            throw new IllegalStateException("Esta persona ya ha votado");
        }

        Persona votante = personaRepository.findById(personaId)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado"));

        Voto voto = new Voto();
        voto.setVotante(votante);
        voto.setPartido(partido);

        return votoRepository.save(voto);
    }

    private void validarHorarioVotacion() {
        ZoneId zone = ZoneId.of("America/Guayaquil");  // Ecuador timezone
        LocalTime now = LocalTime.now(zone);
        LocalTime inicio = LocalTime.of(7, 0); // 7:00 AM
        LocalTime fin = LocalTime.of(17, 0);   // 5:00 PM

        if (now.isBefore(inicio)){
            throw new IllegalStateException("La votación comienza a las 7:00 AM");
        } else if (now.isAfter(fin)) {
            throw new IllegalStateException("La votación ha finalizado (horario: 7:00 AM - 5:00 PM)");
        }
    }

    public boolean haVotado(Long personaId) {
        return votoRepository.existsByVotanteId(personaId);
    }
}
