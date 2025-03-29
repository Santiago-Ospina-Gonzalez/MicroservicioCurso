package com.example.cursoservices.repository;

import com.example.cursoservices.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}