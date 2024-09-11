package com.JavaCoffee.BackEndJC.model.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cardapio")
@SQLDelete(sql = "UPDATE cardapio SET status = 'Inativo' WHERE id = ?")
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

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo"; 
    
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
