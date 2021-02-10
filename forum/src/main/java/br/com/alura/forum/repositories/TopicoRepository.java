package br.com.alura.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.entities.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	
	List<Topico> findByCursoNome(String nomeCurso);
	
	//CENÁRIOS IMPORTANTES:
	
	/*AMBIGUIDADE ENTRE OS NOMES DO METODO
	 * Caso em meu classe curso tiver um atributo CursoNome irá gerar ambiguidade
	 * como resolver esse problema? uma a solução para definir o atributo de uma
	 * entidade de outra entidade (JOIN) será utilizar o (_) entre Curso e nome, a
	 * chamada ficará assim: 
	 * 
	 * List<Topico> findByCurso_Nome(String nomeCurso);
	 */
	
	/* NOMES DA CHAMADAS PERSONALIZADOS (FUGINDO DA CONVENÇÃO)
	 * Caso queira utilizar palavras em portugues tera que criar um query e montas
	 * sua JPQL e utilizar as anottations @Query e @Param como no exemplo abaixo
	 * 
	 * @Query("SELECT t FROM Topic t where t.curso.nome = :nomeCurso") List<Topico>
	 * buscarTopicoPorNomeCurso(@Param("nomeCurso") String nomeCurso);
	 */
	
	
	
}
