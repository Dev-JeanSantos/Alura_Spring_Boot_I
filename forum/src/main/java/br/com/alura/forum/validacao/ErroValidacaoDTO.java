package br.com.alura.forum.validacao;

public class ErroValidacaoDTO {
	
	private String mensagem;
	private String erro;
	
	public ErroValidacaoDTO(String mensagem, String erro) {
		this.mensagem = mensagem;
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}	
}
