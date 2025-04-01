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

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @Column(nullable = false)
    private Integer duracionHoras;

    @Column(nullable = false)
    private String modalidad;

    @Column(nullable = false)
    private Integer cuposDisponibles;

    @Column(nullable = false)
    private String horario;

    @Column(name = "id_semestre", nullable = false)
    private Long idSemestre;

    @Column(nullable = false)
    private Byte numeroCreditos;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion = LocalDate.now();
}