package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.dtos.RegistroDTO;
import com.example.votacionpresidencial.models.Rol;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.repositories.RolRepository;
import com.example.votacionpresidencial.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarAdmin(RegistroDTO registroDTO) {
        if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        Rol rolAdmin = rolRepository.findByNombre("ADMIN")
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no configurado"));

        Usuario admin = new Usuario();
        admin.setUsername(registroDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        admin.setRol(rolAdmin);
        admin.setPersona(null);

        usuarioRepository.save(admin);
    }
}
