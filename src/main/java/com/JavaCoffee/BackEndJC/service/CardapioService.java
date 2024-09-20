package com.JavaCoffee.BackEndJC.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.JavaCoffee.BackEndJC.dto.CardapioDTO;
import com.JavaCoffee.BackEndJC.dto.mapper.CardapioMapper;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;
import com.JavaCoffee.BackEndJC.model.entities.Cardapio;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
@Validated
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
		return cardapioRepository.findById(id).map(cardapioMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public CardapioDTO novoItem(@Valid CardapioDTO cardapio) {
		return cardapioMapper.toDTO(cardapioRepository.save(cardapioMapper.toEntity(cardapio)));
	}
	
	public CardapioDTO editarItem(@Positive int id,@Valid Cardapio cardapio) {
	    return cardapioRepository.findById(id)
	        .map(record -> {
	            record.setNome(cardapio.getNome());
	            record.setDescricao(cardapio.getDescricao());
	            record.setPreco(cardapio.getPreco());
	            record.setImagem(cardapio.getImagem());
	            record.setCategoria(cardapio.getCategoria());
	            return cardapioMapper.toDTO(cardapioRepository.save(cardapio));
	        }).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@PathVariable int id) {
		cardapioRepository.delete(cardapioRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
	
}
