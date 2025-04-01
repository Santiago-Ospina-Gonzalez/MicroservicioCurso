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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Tag(name = "Controlador de Cursos", description = "API para la gestión de cursos académicos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<CursoResponse> crearCurso(
            @Valid @RequestBody CursoRequest cursoRequest) {
        CursoResponse response = cursoService.crearCurso(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los cursos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente")
    public ResponseEntity<List<CursoResponse>> listarTodosLosCursos() {
        List<CursoResponse> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID")
    @ApiResponse(responseCode = "200", description = "Curso encontrado")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoResponse> obtenerCursoPorId(
            @Parameter(description = "ID del curso a buscar")
            @PathVariable Long id) {
        CursoResponse response = cursoService.obtenerCurso(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso existente")
    @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<CursoResponse> actualizarCurso(
            @Parameter(description = "ID del curso a actualizar")
            @PathVariable Long id,
            @Valid @RequestBody CursoRequest cursoRequest) {
        CursoResponse response = cursoService.actualizarCurso(id, cursoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso")
    @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<Void> eliminarCurso(
            @Parameter(description = "ID del curso a eliminar")
            @PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional de ejemplo
    @GetMapping("/activos")
    @Operation(summary = "Obtener cursos activos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos activos obtenida")
    public ResponseEntity<List<CursoResponse>> listarCursosActivos() {
        List<CursoResponse> cursos = cursoService.listarCursos()
                .stream()
                .filter(CursoResponse::activo)
                .toList();
        return ResponseEntity.ok(cursos);
    }
}