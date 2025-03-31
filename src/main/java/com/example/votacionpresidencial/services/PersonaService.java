package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.models.Rol;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.votacionpresidencial.dtos.PersonaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;

    public boolean existeCedula(String cedula) {
        return personaRepository.existsByCedula(cedula);
    }

    public void guardarPersonaConUsuario(PersonaDTO personaDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(personaDTO.getCedula());
        usuario.setPassword(passwordEncoder.encode(personaDTO.getCedula()));
        Rol rolAdmin = rolRepository.findByNombre("VOTANTE")
                .orElseThrow(() -> new RuntimeException("Rol VOTANTE no configurado"));
        usuario.setRol(rolAdmin); // Asigna rol "VOTANTE"
        usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setCedula(personaDTO.getCedula());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setCiudad(ciudadRepository.findById(personaDTO.getCiudadId()).orElseThrow());
        persona.setGenero(generoRepository.findById(personaDTO.getGeneroId()).orElseThrow());
        persona.setUsuario(usuario);

        personaRepository.save(persona);
    }

    public boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) return false;

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if ((provincia < 1 || provincia > 24) && provincia != 30) return false;

        if (cedula.chars().distinct().count() == 1) return false;

        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int total = 0;

        for (int i = 0; i < 9; i++) {
            int valor = (cedula.charAt(i) - '0') * coeficientes[i];
            total += (valor > 9) ? valor - 9 : valor;
        }

        int digitoVerificador = cedula.charAt(9) - '0';
        int calculado = (10 - (total % 10)) % 10;

        return digitoVerificador == calculado;
    }

    public List<Persona> listarPersonasConRelaciones() {
        return personaRepository.findAllWithRelations();
    }

    public Persona obtenerPersonaConRelaciones(Long id) {
        return personaRepository.findByIdWithRelations(id).orElseThrow();
    }

    @Transactional
    public void actualizarPersona(PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(personaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Persona con ID " + personaDTO.getId() + " no encontrada"));
        persona.setCedula(personaDTO.getCedula());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());

        persona.setCiudad(ciudadRepository.findById(personaDTO.getCiudadId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada")));

        persona.setGenero(generoRepository.findById(personaDTO.getGeneroId())
                .orElseThrow(() -> new EntityNotFoundException("Género no encontrado")));

        Usuario usuario = Optional.ofNullable(persona.getUsuario())
                .orElseThrow(() -> new IllegalStateException("La persona no tiene usuario asociado"));

        usuario.setUsername(personaDTO.getCedula());

        if (StringUtils.hasText(personaDTO.getCedula())) {
            usuario.setPassword(passwordEncoder.encode(personaDTO.getCedula()));
        }

        personaRepository.save(persona);
    }

    @Transactional
    public void eliminarPersona(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));
        if (persona.getUsuario() != null) {
            usuarioRepository.delete(persona.getUsuario());
        }

        personaRepository.delete(persona);
    }


}
