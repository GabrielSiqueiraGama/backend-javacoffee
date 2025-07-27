package com.JavaCoffee.BackEndJC.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.JavaCoffee.BackEndJC.dto.IngredienteDTO;
import com.JavaCoffee.BackEndJC.dto.ProtutoDTO;
import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.model.entities.Produto;

@Component 
public class ProdutoMapper {

	public ProtutoDTO toDTO(Produto produto) {
		if (produto == null) {
			return null;
		}
		List<IngredienteDTO> ingredientes = produto.getIngredientes().stream()
				.map(ingrediente -> new IngredienteDTO(ingrediente.getId(), ingrediente.getNome(), ingrediente.getDescricao()))
				.collect(Collectors.toList());
		return new ProtutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getImagem(), produto.getCategoria().getValue(), ingredientes); 
	}
	
	public Produto toEntity(ProtutoDTO produtoDTO) {
		
		if(produtoDTO == null) {
			return null;
		}
		
		Produto produto = new Produto();
		
		produto.setId(produtoDTO.id());
		produto.setNome(produtoDTO.nome());
		produto.setPreco(produtoDTO.preco());
		produto.setDescricao(produtoDTO.descricao());
		produto.setCategoria(convertCategoryValue(produtoDTO.categoria()));
		produto.setImagem(produtoDTO.imagem());
		
		return produto;
	}
	
	public Category convertCategoryValue(String value) {
		if(value == null) {
			return null;
		}
		return switch (value) {
			case "Lanche" -> Category.LANCHE;
			case "Bebida"-> Category.BEBIDA;
			case "Duplo" -> Category.DUPLO;
			default -> throw new IllegalArgumentException("Categoria invalida: " + value);
		};
	}
}
