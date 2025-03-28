package com.example.votacionpresidencial.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "provincias")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Ciudad> ciudades;
}