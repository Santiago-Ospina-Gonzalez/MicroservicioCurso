package com.example.cursoservices.dto;

import java.time.LocalDate;

public record CursoResponse(
        Long id,
        String nombre,
        String descripcion,
        Long idDocente,
        Integer duracionHoras,
        String modalidad,
        Integer cuposDisponibles,
        String horario,
        Long idSemestre,
        Byte numeroCreditos,
        Boolean activo,
        LocalDate fechaCreacion
) {}