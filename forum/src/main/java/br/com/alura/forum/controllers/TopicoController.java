package br.com.alura.forum.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.entities.Curso;
import br.com.alura.forum.entities.Topico;

//Assim o spring assume que é um metodo ResponseBody, sem a necessidade de inserir nos metodos da classe
@RestController
public class TopicoController {
	
	@RequestMapping("/topicos")
	public List<TopicoDTO> list() {
		 Topico topico = new Topico("Duvidas", "Duvidas como spring", new Curso("Spring", "Programacao"));
		 Topico topico2 = new Topico("Duvidas", "Duvidas como collectios", new Curso("Spring", "Programacao"));
		 Topico topico3 = new Topico("Duvidas", "Duvidas como anottations", new Curso("Spring", "Programacao"));
		 Topico topico4 = new Topico("Duvidas", "Duvidas como DevTolls", new Curso("Spring", "Programacao"));
		 
		 return TopicoDTO.converter(Arrays.asList(topico, topico2, topico3, topico4 ));
	
	}
}
