package com.example.cursoservices.controller;

import com.example.cursoservices.dto.CursoRequest;
import com.example.cursoservices.dto.CursoResponse;
import com.example.cursoservices.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso-service")
@RequiredArgsConstructor
@Tag(name = "Controlador de Cursos", description = "API para la gestión de cursos académicos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/cursos")
    @Operation(summary = "Crear un nuevo curso")
    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<CursoResponse> crearCurso(
            @Valid @RequestBody CursoRequest cursoRequest) {
        CursoResponse response = cursoService.crearCurso(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cursos")
    @Operation(summary = "Obtener todos los cursos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente")
    public ResponseEntity<List<CursoResponse>> listarTodosLosCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/cursos/{id}")
    @Operation(summary = "Obtener un curso por ID")
    @ApiResponse(responseCode = "200", description = "Curso encontrado")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoResponse> obtenerCursoPorId(
            @Parameter(description = "ID del curso a buscar")
            @PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerCurso(id));
    }

    @PutMapping("/cursos")
    @Operation(summary = "Actualizar un curso existente")
    @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoResponse> actualizarCurso(
            @Valid @RequestBody CursoRequest cursoRequest) {
        return ResponseEntity.ok(cursoService.actualizarCurso(cursoRequest));
    }

    @DeleteMapping("/cursos")
    @Operation(summary = "Eliminar un curso")
    @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<Void> eliminarCurso(
            @Parameter(description = "ID del curso a eliminar")
            @RequestParam Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/curso/page/{page}")
    @Operation(summary = "Obtener cursos paginados")
    @ApiResponse(responseCode = "200", description = "Cursos paginados obtenidos exitosamente")
    public ResponseEntity<Page<CursoResponse>> listarCursosPaginados(
            @Parameter(description = "Número de página (0-based)")
            @PathVariable int page,
            @Parameter(description = "Tamaño de la página")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(cursoService.listarCursosPaginados(page, size));
    }

    @GetMapping("/cursos/semestre/{idSemestre}")
    @Operation(summary = "Obtener cursos por semestre")
    public ResponseEntity<List<CursoResponse>> listarCursosPorSemestre(
            @PathVariable Long idSemestre) {
        return ResponseEntity.ok(cursoService.listarCursosPorSemestre(idSemestre));
    }

    @GetMapping("/cursos/docente/{idDocente}")
    @Operation(summary = "Obtener cursos por docente")
    public ResponseEntity<List<CursoResponse>> listarCursosPorDocente(
            @PathVariable Long idDocente) {
        return ResponseEntity.ok(cursoService.listarCursosPorDocente(idDocente));
    }
}