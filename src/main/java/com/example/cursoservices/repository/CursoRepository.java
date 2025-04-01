package com.example.cursoservices.repository;

import com.example.cursoservices.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByIdSemestre(Long idSemestre);
    List<Curso> findByIdDocente(Long idDocente);
}