package com.example.votacionpresidencial.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "votos", uniqueConstraints = @UniqueConstraint(columnNames = {"votante_id"}))
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "votante_id", nullable = false)
    private Persona votante;

    @ManyToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaVoto;
}