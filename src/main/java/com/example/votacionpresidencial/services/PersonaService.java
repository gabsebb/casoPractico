package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.models.Rol;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.votacionpresidencial.dtos.PersonaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        usuario.setUsername(personaDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(personaDTO.getPassword()));
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
        if (cedula == null || cedula.length() != 10) return false;

        try {
            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int total = 0;

            for (int i = 0; i < 9; i++) {
                int valor = Integer.parseInt(cedula.substring(i, i+1)) * coeficientes[i];
                total += (valor > 9) ? valor - 9 : valor;
            }

            int digitoVerificador = Integer.parseInt(cedula.substring(9));
            int calculado = (10 - (total % 10)) % 10;

            return digitoVerificador == calculado;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Persona> listarPersonasConRelaciones() {
        return personaRepository.findAllWithRelations();
    }
}
