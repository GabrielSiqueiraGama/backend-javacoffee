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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
