package com.example.votacionpresidencial.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Persona> personas;
}
