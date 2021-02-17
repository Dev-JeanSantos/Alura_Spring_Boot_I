package br.com.alura.forum.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.entities.Curso;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository repository;
	
	@Test
	public void deveriaBuscarUmCursoPeloSeuNome() {
		
		String nomeCurso = "HTML 5";
		Curso curso = repository.findByNome(nomeCurso);
		Assertions.assertNotNull(curso);
		Assertions.assertEquals(nomeCurso, curso.getNome());
		
	}
	@Test
	public void NãoCarregarUmCursoPeloSeuNome() {
		
		String nomeCurso = "HTML";
		Curso curso = repository.findByNome(nomeCurso);
		Assertions.assertNull(curso);
		Assertions.assertNotEquals(nomeCurso, null);
		
	}

}
