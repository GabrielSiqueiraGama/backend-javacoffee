package com.JavaCoffee.BackEndJC.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "combo")
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private int id;

    @NotNull
    private String nome;
    @NotNull
    private Double preco;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return "Combo [id=" + id + ", nome=" + nome + ", preco=" + preco + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getPreco()=" + getPreco() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public Combo(int id, @NotNull String nome, @NotNull Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	public Combo() {
		super();
	}
	public Combo(@NotNull String nome, @NotNull Double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}
    
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "combo")
    //private List<Produto> produtos = new ArrayList<Produto>();
    
    
}
