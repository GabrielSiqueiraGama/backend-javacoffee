package com.JavaCoffee.BackEndJC.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.enums.Status;
import com.JavaCoffee.BackEndJC.enums.converters.CategoryConverter;
import com.JavaCoffee.BackEndJC.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;


@Entity(name = "produto")
//@SQLDelete(sql = "UPDATE produto SET status = 'Inativo' WHERE id = ?")
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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "produto")//Aqui coloca como cascata, a alteração realizada nessa entidade pode afetar a filha e caso seja excluida a filha também é.
    //@JoinColumn(name = "ingredientes")
    private List<ProdutoIngrediente> ingredientes = new ArrayList<ProdutoIngrediente>();
    
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "combo_id", nullable = false)
	//private Combo combo;
    


	public Produto(Produto produto) {
	}

	public Produto() {
	}



	public Produto(int id, @NotNull String nome, @NotNull Double preco, @NotNull String descricao,
			@NotNull String imagem, Category categoria, @NotNull Status status, List<ProdutoIngrediente> ingredientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.imagem = imagem;
		this.categoria = categoria;
		this.status = status;
		this.ingredientes = ingredientes;
	}
	public Produto( @NotNull String nome, @NotNull Double preco, @NotNull String descricao,
			@NotNull String imagem, Category categoria, @NotNull Status status, List<ProdutoIngrediente> ingredientes) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.imagem = imagem;
		this.categoria = categoria;
		this.status = status;
		this.ingredientes = ingredientes;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Category getCategoria() {
		return categoria;
	}

	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProdutoIngrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<ProdutoIngrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao + ", imagem="
				+ imagem + ", categoria=" + categoria + ", status=" + status + ", ingredientes=" + ingredientes
				+ ", getId()=" + getId() + ", getNome()=" + getNome() + ", getPreco()=" + getPreco()
				+ ", getDescricao()=" + getDescricao() + ", getImagem()=" + getImagem() + ", getCategoria()="
				+ getCategoria() + ", getStatus()=" + getStatus() + ", getIngredientes()=" + getIngredientes()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
