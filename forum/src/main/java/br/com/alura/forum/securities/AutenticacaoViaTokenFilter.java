package br.com.alura.forum.securities;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.entities.Usuario;
import br.com.alura.forum.repositories.UsuarioRepository;
import br.com.alura.forum.service.securities.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valid = tokenService.isTokenValido(token);
		
		if (valid) {
			
			autenticarCliente(token);
		}
		
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliente(String token) {
		
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		
		UsernamePasswordAuthenticationToken authentication = new 
				UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String tokenHeader = request.getHeader("Authorization");
		
		if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")) {
			
			return null;
		}
		
		return tokenHeader.substring(7, tokenHeader.length());
	}
	
	
}
