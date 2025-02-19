package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.dto.AuthDTO;
import com.JavaCoffee.BackEndJC.dto.LoginResponseDTO;
import com.JavaCoffee.BackEndJC.dto.RegisterDTO;
import com.JavaCoffee.BackEndJC.model.entities.User;
import com.JavaCoffee.BackEndJC.model.repositories.UserRepository;
import com.JavaCoffee.BackEndJC.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(userNamePassword); 
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build(); //Confere se há uma conta criada com este login antes de registrar.
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());//encripta a senha no banco de daodos
		User newUser = new User(data.login(), encryptedPassword, data.role());
		this.repository.save(newUser);
		return ResponseEntity.ok().build();
	}
}
