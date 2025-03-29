package com.example.cursoservices.service;

import com.example.cursoservices.dto.CursoRequest;
import com.example.cursoservices.dto.CursoResponse;
import com.example.cursoservices.exception.ResourceNotFoundException;
import com.example.cursoservices.model.Curso;
import com.example.cursoservices.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    @Transactional
    public CursoResponse crearCurso(CursoRequest cursoRequest) {
        // Validación adicional si es necesaria
        if (cursoRequest.cuposDisponibles() < 0) {
            throw new IllegalArgumentException("Los cupos disponibles no pueden ser negativos");
        }

        Curso curso = mapToEntity(cursoRequest);
        curso = cursoRepository.save(curso);
        return mapToResponse(curso);
    }

    @Transactional(readOnly = true)
    public List<CursoResponse> listarTodosLosCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CursoResponse obtenerCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));
        return mapToResponse(curso);
    }

    @Transactional
    public CursoResponse actualizarCurso(Long id, CursoRequest cursoRequest) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", id));

        actualizarEntityDesdeRequest(cursoRequest, cursoExistente);
        Curso cursoActualizado = cursoRepository.save(cursoExistente);
        return mapToResponse(cursoActualizado);
    }

    @Transactional
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Curso", "id", id);
        }
        cursoRepository.deleteById(id);
    }

    // Métodos auxiliares de mapeo
    private Curso mapToEntity(CursoRequest request) {
        Curso curso = new Curso();
        curso.setNombre(request.nombre());
        curso.setDescripcion(request.descripcion());
        curso.setDocente(request.docente());
        curso.setDuracionHoras(request.duracionHoras());
        curso.setModalidad(request.modalidad());
        curso.setCuposDisponibles(request.cuposDisponibles());
        curso.setHorario(request.horario());
        curso.setRequisitos(request.requisitos());
        curso.setActivo(request.activo());
        return curso;
    }

    private void actualizarEntityDesdeRequest(CursoRequest request, Curso entity) {
        entity.setNombre(request.nombre());
        entity.setDescripcion(request.descripcion());
        entity.setDocente(request.docente());
        entity.setDuracionHoras(request.duracionHoras());
        entity.setModalidad(request.modalidad());
        entity.setCuposDisponibles(request.cuposDisponibles());
        entity.setHorario(request.horario());
        entity.setRequisitos(request.requisitos());
        entity.setActivo(request.activo());
    }

    private CursoResponse mapToResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getDocente(),
                curso.getDuracionHoras(),
                curso.getModalidad(),
                curso.getCuposDisponibles(),
                curso.getHorario(),
                curso.getRequisitos(),
                curso.getActivo(),
                curso.getFechaCreacion()
        );
    }
}