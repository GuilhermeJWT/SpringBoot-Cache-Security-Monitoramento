package br.com.systemsgs.forum.controller;

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

import br.com.systemsgs.forum.controller.form.LoginForm;
import br.com.systemsgs.service.GerarTokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private GerarTokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication autenticacao = manager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(autenticacao);
			System.out.println(token);
			
			return ResponseEntity.ok().build();
		}catch(AuthenticationException e ) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
