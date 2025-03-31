package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.dtos.PersonaDTO;
import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.services.CiudadService;
import com.example.votacionpresidencial.services.GeneroService;
import com.example.votacionpresidencial.services.PersonaService;
import com.example.votacionpresidencial.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private GeneroService generoService;



    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        List<Persona> personas = personaService.listarPersonasConRelaciones();
        model.addAttribute("listaPersonas", personas);
        model.addAttribute("persona", new PersonaDTO());
        model.addAttribute("ciudades", ciudadService.listarTodas());
        model.addAttribute("generos", generoService.listarTodos());
        return "admin/formulario_persona";
    }

    // Procesar creación
    @PostMapping("/guardar")
    public String guardarPersona(
            @Valid @ModelAttribute("persona") PersonaDTO personaDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Validar cédula única
        if (personaService.existeCedula(personaDTO.getCedula())) {
            result.rejectValue("cedula", "error.cedula", "La cédula ya está registrada");
        }
        if (!personaService.validarCedulaEcuatoriana(personaDTO.getCedula())) {
            result.rejectValue("cedula", "error.cedula", "Cédula inválida");
        }
        if (result.hasErrors()) {
            model.addAttribute("ciudades", ciudadService.listarTodas());
            model.addAttribute("generos", generoService.listarTodos());
            return "admin/formulario_persona";
        }

        personaService.guardarPersonaConUsuario(personaDTO);
        redirectAttributes.addFlashAttribute("success", "Persona registrada exitosamente");
        return "redirect:/nuevo";
    }
}
