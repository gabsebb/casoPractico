package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.dtos.RegistroDTO;
import com.example.votacionpresidencial.models.Rol;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.repositories.RolRepository;
import com.example.votacionpresidencial.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Usuario> listarusuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void actualizarUsuario(Long id, Usuario usuarioActualizado, String nuevaPassword) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        usuarioExistente.setUsername(usuarioActualizado.getUsername());
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(nuevaPassword));
        }
        usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(usuario -> {
            if (usuario.getPersona() != null) {
                usuario.setPersona(null);
            }
        });
        usuarioRepository.deleteById(id);
    }
}
