package com.JavaCoffee.BackEndJC.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name ="ingrediente")

public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public Ingrediente(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Ingrediente(String nome) {
		super();
		this.nome = nome;
	}

	public Ingrediente() {
	}
	
	
	
}
