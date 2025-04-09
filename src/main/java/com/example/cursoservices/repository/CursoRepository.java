package com.example.cursoservices.repository;

import com.example.cursoservices.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByIdSemestre(Long idSemestre);
    List<Curso> findByIdDocente(Long idDocente);
    Page<Curso> findAll(Pageable pageable);
}