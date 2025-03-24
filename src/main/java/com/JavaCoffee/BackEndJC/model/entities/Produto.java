package com.JavaCoffee.BackEndJC.model.entities;

import org.hibernate.annotations.SQLDelete;

import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.enums.Status;
import com.JavaCoffee.BackEndJC.enums.converters.CategoryConverter;
import com.JavaCoffee.BackEndJC.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produto")
@SQLDelete(sql = "UPDATE produto SET status = 'Inativo' WHERE id = ?")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private int id;

    @NotNull
    private String nome;
    @NotNull
    private Double preco;
    @NotNull
    private String descricao;
    @NotNull
    private String imagem;
    
    //@Pattern(regexp = "Bebida| Lanche| Duplo")
    //@Enumerated(EnumType.ORDINAL)//salva o enum como numero, precisa de update
    //@Enumerated(EnumType.STRING) salva como string, precisa de update
    @Convert(converter = CategoryConverter.class)
    private Category categoria;

    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO; 
    
	public Produto(String nome, Double preco, String descricao, String imagem, @Pattern(regexp = "Bebida| Lanche| Duplo") Category categoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
	}

	public Produto(Produto produto) {
		// TODO Auto-generated constructor stub
	}
}
