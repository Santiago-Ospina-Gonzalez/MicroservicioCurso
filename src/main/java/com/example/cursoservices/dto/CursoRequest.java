package com.example.cursoservices.dto;

import jakarta.validation.constraints.*;

public record CursoRequest(
        Long id, // Nuevo campo para actualizaciones

        @NotBlank String nombre,
        @NotBlank @Size(max = 500) String descripcion,
        @NotNull Long idDocente,
        @NotNull @Positive Integer duracionHoras,
        @NotBlank String modalidad,
        @NotNull @PositiveOrZero Integer cuposDisponibles,
        @NotBlank String horario,
        @NotNull Long idSemestre,
        @NotNull @Min(1) @Max(127) Byte numeroCreditos
) {}