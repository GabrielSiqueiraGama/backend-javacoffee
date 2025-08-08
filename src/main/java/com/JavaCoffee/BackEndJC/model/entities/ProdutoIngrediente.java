package com.JavaCoffee.BackEndJC.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "produto_ingrediente")
public class ProdutoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public ProdutoIngrediente(Long id, Produto produto, Ingrediente ingrediente) {
		super();
		this.id = id;
		this.produto = produto;
		this.ingrediente = ingrediente;
	}

	public ProdutoIngrediente(Produto produto, Ingrediente ingrediente) {
		super();
		this.produto = produto;
		this.ingrediente = ingrediente;
	}

	public ProdutoIngrediente() {
		super();
	}
    
    
}
