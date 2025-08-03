package com.JavaCoffee.BackEndJC.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.JavaCoffee.BackEndJC.dto.IngredienteDTO;
import com.JavaCoffee.BackEndJC.dto.ProdutoDTO;
import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.model.entities.Ingrediente;
import com.JavaCoffee.BackEndJC.model.entities.Produto;
import com.JavaCoffee.BackEndJC.model.entities.ProdutoIngrediente;
import com.JavaCoffee.BackEndJC.model.repositories.IngredienteRepository;

@Component 
public class ProdutoMapper {

	private final IngredienteRepository ingredienteRepository;

	public ProdutoMapper(IngredienteRepository ingredienteRepository) {
		this.ingredienteRepository = ingredienteRepository;
	}
	public ProdutoDTO toDTO(Produto produto) {
		if (produto == null) {
			return null;
		}
		List<IngredienteDTO> ingredientes = produto.getIngredientes().stream()
			    .map(produtoIngrediente -> {
			        Ingrediente ingrediente = produtoIngrediente.getIngrediente();
			        return new IngredienteDTO(ingrediente.getId(), ingrediente.getNome());
			    })
			    .collect(Collectors.toList());

		return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getImagem(), produto.getCategoria().getValue(), ingredientes); 
	}
	
	public Produto toEntity(ProdutoDTO produtoDTO) {
		
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
		
		List<ProdutoIngrediente> ingredientes = produtoDTO.ingredientes().stream().map(ingredienteDTO -> {
		    ProdutoIngrediente pi = new ProdutoIngrediente();
		    
		    Ingrediente ingrediente = ingredienteRepository
		    	    .findByNome(ingredienteDTO.nome())
		    	    .orElseGet(() -> ingredienteRepository.save(
		    	        new Ingrediente(null, ingredienteDTO.nome())
		    	    ));

		    pi.setIngrediente(ingrediente);
		    pi.setProduto(produto);

		    return pi;
		}).collect(Collectors.toList());
		produto.setIngredientes(ingredientes);
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
