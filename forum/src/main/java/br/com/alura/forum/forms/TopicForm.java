package br.com.alura.forum.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.entities.Curso;
import br.com.alura.forum.entities.Topico;
import br.com.alura.forum.repositories.CursoRepository;

public class TopicForm {
	
	@NotNull @NotEmpty @Size(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 10)
	private String mensagem;
	@NotNull @NotEmpty
	private String nomeCurso;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converter( CursoRepository cursoRepository) {
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
}
