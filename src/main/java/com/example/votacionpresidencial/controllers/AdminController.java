package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.dtos.RegistroDTO;
import com.example.votacionpresidencial.models.Usuario;
import com.example.votacionpresidencial.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/admin")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarios", usuarioService.listarusuarios());
        model.addAttribute("registroDTO", new RegistroDTO());
        return "admin/crear_admin";
    }

    @PostMapping("/admin")
    public String registrarAdmin(@Valid @ModelAttribute("registroDTO") RegistroDTO registroDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/crear_admin";
        }
        try {
            usuarioService.registrarAdmin(registroDTO);
            return "redirect:/admin?registroExitoso";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin";
        }
    }

    @GetMapping("/editarUsu/{id}")
    public String mostrarEdicion(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "admin/editar";
    }

    @PostMapping("/editarUsu/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                    @RequestParam(required = false) String password,
                                    @ModelAttribute("usuario") Usuario usuarioActualizado) {
        try {
        usuarioService.actualizarUsuario(id, usuarioActualizado, password);
        return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            return "redirect:admin/editar";
        }
    }

    @GetMapping("/eliminarUsu/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        try {
        usuarioService.eliminarUsuario(id);
        return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            return "acceso-denegado";
        }
    }

}
