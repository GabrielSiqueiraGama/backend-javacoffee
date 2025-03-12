package com.JavaCoffee.BackEndJC.dto.mapper;

import org.springframework.stereotype.Component;

import com.JavaCoffee.BackEndJC.dto.ProtutoDTO;
import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.model.entities.Produto;

@Component 
public class ProdutoMapper {

	public ProtutoDTO toDTO(Produto produto) {
		if (produto == null) {
			return null;
		}
		return new ProtutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getImagem(), "BEBIDA"); 
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
		produto.setCategoria(Category.BEBIDA);
		produto.setImagem(produtoDTO.imagem());
		
		return produto;
	}
}
