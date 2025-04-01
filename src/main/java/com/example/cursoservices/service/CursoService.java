package com.example.cursoservices.service;

import com.example.cursoservices.dto.CursoRequest;
import com.example.cursoservices.dto.CursoResponse;
import com.example.cursoservices.exception.ResourceNotFoundException;
import com.example.cursoservices.model.Curso;
import com.example.cursoservices.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public CursoResponse crearCurso(CursoRequest request) {
        validarDatosCurso(request);

        Curso curso = new Curso();
        curso.setNombre(request.nombre());
        curso.setDescripcion(request.descripcion());
        curso.setIdDocente(request.idDocente());
        curso.setDuracionHoras(request.duracionHoras());
        curso.setModalidad(request.modalidad());
        curso.setCuposDisponibles(request.cuposDisponibles());
        curso.setHorario(request.horario());
        curso.setIdSemestre(request.idSemestre());
        curso.setNumeroCreditos(request.numeroCreditos());
        curso.setActivo(true);
        curso.setFechaCreacion(LocalDate.now());

        curso = cursoRepository.save(curso);
        return mapToResponse(curso);
    }

    @Transactional(readOnly = true)
    public List<CursoResponse> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CursoResponse obtenerCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
        return mapToResponse(curso);
    }

    @Transactional
    public CursoResponse actualizarCurso(Long id, CursoRequest request) {
        validarDatosCurso(request);

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));

        curso.setNombre(request.nombre());
        curso.setDescripcion(request.descripcion());
        curso.setIdDocente(request.idDocente());
        curso.setDuracionHoras(request.duracionHoras());
        curso.setModalidad(request.modalidad());
        curso.setCuposDisponibles(request.cuposDisponibles());
        curso.setHorario(request.horario());
        curso.setIdSemestre(request.idSemestre());
        curso.setNumeroCreditos(request.numeroCreditos());

        curso = cursoRepository.save(curso);
        return mapToResponse(curso);
    }

    @Transactional
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CursoResponse> listarCursosPorSemestre(Long idSemestre) {
        return cursoRepository.findByIdSemestre(idSemestre).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CursoResponse> listarCursosPorDocente(Long idDocente) {
        return cursoRepository.findByIdDocente(idDocente).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // --- Métodos auxiliares ---
    private CursoResponse mapToResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getIdDocente(),
                curso.getDuracionHoras(),
                curso.getModalidad(),
                curso.getCuposDisponibles(),
                curso.getHorario(),
                curso.getIdSemestre(),
                curso.getNumeroCreditos(),
                curso.getActivo(),
                curso.getFechaCreacion()
        );
    }

    private void validarDatosCurso(CursoRequest request) {
        if (request.cuposDisponibles() < 0) {
            throw new IllegalArgumentException("Los cupos disponibles no pueden ser negativos");
        }
        if (request.duracionHoras() <= 0) {
            throw new IllegalArgumentException("La duración en horas debe ser mayor a 0");
        }
    }
}