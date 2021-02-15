package br.com.alura.forum.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.entities.Topico;
import br.com.alura.forum.repositories.TopicoRepository;

public class AtualizacaoTopicForm {
	
	@NotNull @NotEmpty @Size(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 10)
	private String mensagem;
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
	public Topico atualizar(Long id, TopicoRepository repository) {
		
		Topico topico = repository.getOne(id);
		
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem );
		
		return topico;
	}
	
}
