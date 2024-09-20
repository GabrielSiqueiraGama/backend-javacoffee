package com.JavaCoffee.BackEndJC.dto.mapper;

import org.springframework.stereotype.Component;

import com.JavaCoffee.BackEndJC.dto.CardapioDTO;
import com.JavaCoffee.BackEndJC.model.entities.Cardapio;

@Component 
public class CardapioMapper {

	public CardapioDTO toDTO(Cardapio cardapio) {
		if (cardapio == null) {
			return null;
		}
		return new CardapioDTO(cardapio.getId(), cardapio.getNome(), cardapio.getPreco(), cardapio.getDescricao(), cardapio.getImagem(), cardapio.getCategoria()); 
	}
	
	public Cardapio toEntity(CardapioDTO cardapioDTO) {
		
		if(cardapioDTO == null) {
			return null;
		}
		
		Cardapio cardapio = new Cardapio();
		
		cardapio.setId(cardapioDTO.id());
		cardapio.setNome(cardapioDTO.nome());
		cardapio.setPreco(cardapioDTO.preco());
		cardapio.setDescricao(cardapioDTO.descricao());
		cardapio.setCategoria(cardapioDTO.categoria());
		cardapio.setImagem(cardapioDTO.imagem());
		
		return cardapio;
	}
}
