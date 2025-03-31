package com.example.votacionpresidencial.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonaDTO {
    private Long id;

    @NotBlank(message = "Cédula es obligatoria")
    @Size(min = 10, max = 10, message = "La cédula debe tener 10 dígitos")
    private String cedula;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @NotNull(message = "Ciudad es obligatoria")
    private Long ciudadId;

    @NotNull(message = "Género es obligatorio")
    private Long generoId;

    private String username;

    private String password;
}
