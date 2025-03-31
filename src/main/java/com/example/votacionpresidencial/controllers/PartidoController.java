package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.services.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/partidos")
@RequiredArgsConstructor
public class PartidoController {
    private final PartidoService partidoService;

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("partidos", partidoService.listarPartidos());
        model.addAttribute("partidoN", new Partido());
        return "partidos/crear_partido";
    }

    @PostMapping("/guardar")
    public String guardarPartido(@Valid @ModelAttribute Partido partido,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "partidos/crear_partido";
        }
        partidoService.crearPartido(partido);
        return "redirect:/partidos/nuevo";
    }

    @GetMapping("/{id}/candidatos")
    public String verCandidatosDelPartido(@PathVariable Long id, Model model) {
        Partido partido = partidoService.obtenerPartidoConCandidatos(id);
        model.addAttribute("partido", partido);
        model.addAttribute("candidatos", partido.getCandidatos());
        return "partidos/lista_candidatos";
    }
}
