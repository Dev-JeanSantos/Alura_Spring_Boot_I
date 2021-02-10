package br.com.alura.forum.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.entities.Curso;
import br.com.alura.forum.entities.Topico;

@Controller
public class TopicoController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> list() {
		 Topico topico = new Topico("Duvidas", "Duvidas como spring", new Curso("Spring", "Programacao"));
		 Topico topico2 = new Topico("Duvidas", "Duvidas como collectios", new Curso("Spring", "Programacao"));
		 Topico topico3 = new Topico("Duvidas", "Duvidas como anottations", new Curso("Spring", "Programacao"));
		 
		 
		 return Arrays.asList(topico, topico2, topico3 );
	}
}
