package com.JavaCoffee.BackEndJC.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.JavaCoffee.BackEndJC.dto.CardapioDTO;
import com.JavaCoffee.BackEndJC.dto.mapper.CardapioMapper;
import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CardapioService {

	private CardapioRepository cardapioRepository;
	private CardapioMapper cardapioMapper;
	
	public CardapioService(CardapioRepository cardapioRepository, CardapioMapper cardapioMapper) {
		this.cardapioRepository = cardapioRepository;
		this.cardapioMapper = cardapioMapper;
	}
	
	public List<CardapioDTO> list(){
		return cardapioRepository.findAll()
				.stream()
				.map(cardapioMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public CardapioDTO findById(@PathVariable @Positive int id) {
		return cardapioRepository.findById(id).map(cardapioMapper::toDTO).orElseThrow(() -> new ProductNotFoundException());
	}
	
	public CardapioDTO novoItem(@Valid CardapioDTO cardapio) {
		return cardapioMapper.toDTO(cardapioRepository.save(cardapioMapper.toEntity(cardapio)));
	}
	
	public CardapioDTO editarItem(@Positive int id,@Valid CardapioDTO cardapio) {
	    return cardapioRepository.findById(id)
	        .map(recordFound -> {
	            recordFound.setNome(cardapio.nome());
	            recordFound.setDescricao(cardapio.descricao());
	            recordFound.setPreco(cardapio.preco());
	            recordFound.setImagem(cardapio.imagem());
	            recordFound.setCategoria(Category.BEBIDA);
	            return cardapioMapper.toDTO(cardapioRepository.save(recordFound));
	        }).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@PathVariable int id) {
		cardapioRepository.delete(cardapioRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
	
}
