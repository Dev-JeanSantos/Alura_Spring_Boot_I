package br.com.alura.forum.dto;

public class TokenDTO {

	private String token;
	private String tipo;

	public TokenDTO(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
}
