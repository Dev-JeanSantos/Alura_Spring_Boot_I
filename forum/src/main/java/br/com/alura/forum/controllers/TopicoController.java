package br.com.alura.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.entities.Topico;
import br.com.alura.forum.repositories.TopicoRepository;

//Assim o spring assume que é um metodo ResponseBody, sem a necessidade de inserir nos metodos da classe
@RestController
public class TopicoController {
	
	@Autowired
	private TopicoRepository repository;
	
	@RequestMapping("/topicos")
	public List<TopicoDTO> list() {
		
		List<Topico> topicos = repository.findAll();
		 return TopicoDTO.converter(topicos);
	
	}
}
