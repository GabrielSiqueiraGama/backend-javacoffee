package com.JavaCoffee.BackEndJC.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Entity(name = "cardapio")
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private Double preco;
    private String descricao;
    private String imagem;
    private String categoria;

	public Cardapio(String nome, Double preco, String descricao, String imagem, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
	}
}
