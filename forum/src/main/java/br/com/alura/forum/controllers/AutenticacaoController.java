package br.com.alura.forum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TokenDTO;
import br.com.alura.forum.forms.LoginForm;
import br.com.alura.forum.service.securities.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form){
//		System.out.println(form.getEmail());
//		System.out.println(form.getSenha());
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
		
	}
}
