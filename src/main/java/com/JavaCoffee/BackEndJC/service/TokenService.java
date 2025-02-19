package com.JavaCoffee.BackEndJC.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.JavaCoffee.BackEndJC.model.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")//local de criação
					.withSubject(user.getLogin())//usuario que vai logar
					.withExpiresAt(getExpirationDate())//tempo de expiração
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error ao gerar um novo token");
		}
	}
	
	
	private Instant getExpirationDate() {
		return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
