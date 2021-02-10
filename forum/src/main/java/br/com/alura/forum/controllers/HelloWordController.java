package br.com.alura.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Spring MVC
@Controller
public class HelloWordController {
	
	//Mapeamento (URL) do controller
	@RequestMapping("/")
	//Roda a mensagem no corpo da pagina!
	@ResponseBody
	public String Hello() {
		return "Hello Word!";
	}
}
