package com.example.cursoservices.dto;

import java.time.LocalDate;

public record CursoResponse(
        Long id,
        String nombre,
        String descripcion,
        String docente,
        Integer duracionHoras,
        String modalidad,
        Integer cuposDisponibles,
        String horario,
        String requisitos,
        Boolean activo,
        LocalDate fechaCreacion
) {}