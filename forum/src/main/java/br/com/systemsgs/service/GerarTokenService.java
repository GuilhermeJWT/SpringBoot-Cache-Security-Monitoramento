package br.com.systemsgs.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.systemsgs.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class GerarTokenService {
	
	private static final long EXPIRATION_TIME = 900000000;

	private static final String SECRET = "SenhaExtremamenteSecreta";

	public String gerarToken(Authentication autenticacao) {
		
		System.out.println("CAIU AQUI");
		
		Usuario usuarioLogado = (Usuario) autenticacao.getPrincipal();
		
		Date dataHoje = new Date();
		Date dataExpiracao = new Date(dataHoje.getTime() + EXPIRATION_TIME);
		
		
		return Jwts.builder()
				.setIssuer("Api da Systems GS")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(dataHoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
	}
	
	

}
