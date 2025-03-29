package com.example.cursoservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String docente;

    @Column(nullable = false)
    private Integer duracionHoras;

    @Column(nullable = false)
    private String modalidad;

    @Column(nullable = false)
    private Integer cuposDisponibles;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false)
    private String requisitos;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();
}