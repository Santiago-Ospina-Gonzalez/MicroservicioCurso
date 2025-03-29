package com.example.cursoservices.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CursoRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
        String descripcion,

        @NotBlank(message = "El docente no puede estar vacío")
        String docente,

        @NotNull(message = "La duración no puede ser nula")
        @Positive(message = "La duración debe ser mayor que 0")
        @Max(value = 500, message = "La duración no puede exceder las 500 horas")
        Integer duracionHoras,

        @NotBlank(message = "La modalidad no puede estar vacía")
        String modalidad,

        @NotNull(message = "Los cupos no pueden ser nulos")
        @PositiveOrZero(message = "Los cupos no pueden ser negativos")
        Integer cuposDisponibles,

        @NotBlank(message = "El horario no puede estar vacío")
        String horario,

        @NotBlank(message = "Los requisitos no pueden estar vacíos")
        String requisitos,

        @NotNull(message = "El estado activo no puede ser nulo")
        Boolean activo
) {}