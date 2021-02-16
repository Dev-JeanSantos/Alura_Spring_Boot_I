package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.dto.DetalhesTopicoDTO;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.entities.Topico;
import br.com.alura.forum.forms.AtualizacaoTopicForm;
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
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDTO> list(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		//@RequestParam int pag, @RequestParam int qtd, @RequestParam String ordenacao
		//Pageable paginacao = PageRequest.of(pag, qtd, Direction.DESC, ordenacao);
		
		if (nomeCurso == null) {
			Page<Topico> topicos = repository.findAll(paginacao);
			return TopicoDTO.converter(topicos);
		} else {

			Page<Topico> topicos = repository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDTO.converter(topicos);
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		repository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {

		Optional<Topico> topico = repository.findById(id);

		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicForm form) {

		Optional<Topico> optional = repository.findById(id);

		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, repository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> remover(@PathVariable Long id) {

		Optional<Topico> optional = repository.findById(id);

		if (optional.isPresent()) {

			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
}
