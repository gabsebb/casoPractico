package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.repositories.UsuarioRepository;
import com.example.votacionpresidencial.services.PartidoService;
import com.example.votacionpresidencial.services.PersonaService;
import com.example.votacionpresidencial.services.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VotoController {
    private final VotoService votoService;
    private final PartidoService partidoService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/votar")
    public String procesarVoto(@RequestParam Long partidoId,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            Persona votante = obtenerPersonaDesdeAutenticacion(authentication);
            votoService.registrarVoto(votante.getId(), partidoId);
            redirectAttributes.addFlashAttribute("exito", "¡Voto registrado correctamente!");
            return "redirect:/votacion";  // Make sure this matches your GET endpoint
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/votacion";  // Redirect back to voting page instead of /error
        }
    }

    @GetMapping("/votacion")  // Add this endpoint
    public String mostrarPaginaVotacion(Model model, Authentication authentication) {
        Persona votante = obtenerPersonaDesdeAutenticacion(authentication);
        List<Partido> partidos = partidoService.listarPartidosConCandidatos();
        model.addAttribute("partidos", partidos);
        model.addAttribute("haVotado", votoService.haVotado(votante.getId()));
        return "votantes/votacion";
    }

    private Persona obtenerPersonaDesdeAutenticacion(Authentication authentication) {
        String username = authentication.getName();

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getPersona() == null) {
            throw new IllegalStateException("El usuario no tiene una persona asociada");
        }

        return usuario.getPersona();
    }

}
