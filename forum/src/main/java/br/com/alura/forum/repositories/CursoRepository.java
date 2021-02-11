package br.com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.entities.Curso;

public interface CursoRepository extends  JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
	
}
