package com.example.votacionpresidencial.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "partidos")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "partido", fetch = FetchType.EAGER)
    private List<Candidato> candidatos;

    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<Voto> votos;
}
