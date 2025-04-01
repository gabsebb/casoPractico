package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.dtos.PersonaDTO;
import com.example.votacionpresidencial.models.Persona;
import com.example.votacionpresidencial.services.CiudadService;
import com.example.votacionpresidencial.services.GeneroService;
import com.example.votacionpresidencial.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

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

    @PostMapping("/guardar")
    public String guardarPersona(
            @Valid @ModelAttribute("persona") PersonaDTO personaDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
        // Validar que la cedula no se repita
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
        return "redirect:/personas/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error" + e.getMessage());
            return "redirect:/personas/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {

        Persona persona = personaService.obtenerPersonaConRelaciones(id);

        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(persona.getId());
        personaDTO.setCedula(persona.getCedula());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setCiudadId(persona.getCiudad().getId());
        personaDTO.setGeneroId(persona.getGenero().getId());
        personaDTO.setUsername(persona.getUsuario().getUsername());
        personaDTO.setPassword(persona.getUsuario().getPassword());

        model.addAttribute("ciudades", ciudadService.listarTodas());
        model.addAttribute("generos", generoService.listarTodos());
        model.addAttribute("persona", personaDTO);
        return "admin/editar_persona_form :: form";
    }

    @PostMapping("/actualizar")
    public String actualizarPersona(
            @ModelAttribute PersonaDTO personaDTO,
            RedirectAttributes redirectAttributes) {
        try {
            personaService.actualizarPersona(personaDTO);
            redirectAttributes.addFlashAttribute("success", "Persona actualizada exitosamente");
            return "redirect:/personas/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
            return "redirect:/personas";
        }
    }

    @GetMapping("/eliminarPerso/{id}")
    public String eliminarPersona(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            personaService.eliminarPersona(id);
            return "redirect:/personas/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
            return "acceso-denegado";
        }
    }
}

