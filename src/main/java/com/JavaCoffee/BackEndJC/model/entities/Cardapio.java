package com.JavaCoffee.BackEndJC.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cardapio")
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private int id;

    @NotBlank
    private String nome;
    @NotBlank
    private Double preco;
    @NotBlank
    private String descricao;
    @NotBlank
    private String imagem;
    
    private String categoria;

	public Cardapio(String nome, Double preco, String descricao, String imagem, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
	}

	public Cardapio(Cardapio cardapio) {
		// TODO Auto-generated constructor stub
	}
}
