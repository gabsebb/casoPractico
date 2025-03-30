package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.dtos.RegistroDTO;
import com.example.votacionpresidencial.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("registroDTO", new RegistroDTO());
        return "registro";
    }

    @PostMapping
    public String registrarAdmin(@Valid @ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            usuarioService.registrarAdmin(registroDTO);
            return "redirect:/login?registroExitoso";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
