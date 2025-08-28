package com.JavaCoffee.BackEndJC.model.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@SuppressWarnings("serial")
@Entity(name = "users")
public class User implements UserDetails{
/*	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	login TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL
	role TEXT NOT NULL*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
	private Long id;
    @NotNull
	private String login;
    @NotNull
	private String password;
    @NotNull
	private UserRole role;
    
    public User(String login, String password, UserRole role) {
    	this.login = login;
    	this.password = password;
    	this.role = role;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));//Caso seja admin, vai ter os direitos de admnin e de usuario também.
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public User(Long id, @NotNull String login, @NotNull String password, @NotNull UserRole role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
	}
	
}
