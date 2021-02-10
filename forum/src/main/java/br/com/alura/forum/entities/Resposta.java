package br.com.alura.forum.entities;

import java.time.LocalDateTime;

public class Resposta {
	
	private Long id;
	private String mensagem;
	private Topico topico;
	private LocalDateTime dataCriacao;
	private Usuario autor;
	private Boolean solução = false;
	
	public Resposta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resposta(Long id, String mensagem, Topico topico, LocalDateTime dataCriacao, Usuario autor,
			Boolean solução) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.topico = topico;
		this.dataCriacao = dataCriacao;
		this.autor = autor;
		this.solução = solução;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getSolução() {
		return solução;
	}

	public void setSolução(Boolean solução) {
		this.solução = solução;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta other = (Resposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
