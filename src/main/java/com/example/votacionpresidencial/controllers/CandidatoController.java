package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.models.Candidato;
import com.example.votacionpresidencial.models.Partido;
import com.example.votacionpresidencial.services.CandidatoService;
import com.example.votacionpresidencial.services.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;
    private final PartidoService partidoService;

    @GetMapping("/nuevo")
    public String mostrarFormularioCandidato(Model model) {
        List<Partido> partidos = partidoService.listarPartidos();

        model.addAttribute("candidato", new Candidato());
        model.addAttribute("partidos", partidos);
        model.addAttribute("cargos", List.of("Presidente", "Vicepresidente", "Diputado"));

        return "partidos/formulario_candidato";
    }

    @PostMapping("/guardar")
    public String guardarCandidato(@Valid @ModelAttribute Candidato candidato,
                                   @RequestParam Long partidoId,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "partidos/formulario_candidato";
        }

        candidatoService.crearCandidato(candidato, partidoId);
        return "redirect:/partidos/nuevo";
    }
}
