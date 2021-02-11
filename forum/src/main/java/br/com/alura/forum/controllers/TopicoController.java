package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.entities.Topico;
import br.com.alura.forum.forms.TopicForm;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;

//Assim o spring assume que é um metodo ResponseBody, sem a necessidade de inserir nos metodos da classe
@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDTO> list(String nomeCurso) {
		
		if (nomeCurso == null) {
			List<Topico> topicos = repository.findAll();
			 return TopicoDTO.converter(topicos);
		}else {
			
			List<Topico> topicos = repository.findByCursoNome(nomeCurso);
			 return TopicoDTO.converter(topicos);
		}
	
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody TopicForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		repository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").
				buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
}
