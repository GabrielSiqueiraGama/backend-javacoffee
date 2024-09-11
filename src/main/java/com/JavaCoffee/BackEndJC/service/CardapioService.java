package com.JavaCoffee.BackEndJC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.JavaCoffee.BackEndJC.model.entities.Cardapio;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CardapioService {

	private CardapioRepository cardapioRepository;
	
	public CardapioService(CardapioRepository cardapioRepository) {
		this.cardapioRepository = cardapioRepository;
	}
	
	public List<Cardapio> list(){
		return (List<Cardapio>) cardapioRepository.findAll();
	}
	
	public Optional<Cardapio> findById(@PathVariable @Positive int id) {
		return cardapioRepository.findById(id);
	}
	
	public Cardapio novoItem(@Valid Cardapio cardapio) {
		return cardapioRepository.save(cardapio);
	}
	
	public Optional<Cardapio> editarItem(@Positive int id,@Valid Cardapio cardapio) {
	    return cardapioRepository.findById(id)
	        .map(record -> {
	            record.setNome(cardapio.getNome());
	            record.setDescricao(cardapio.getDescricao());
	            record.setPreco(cardapio.getPreco());
	            record.setImagem(cardapio.getImagem());
	            record.setCategoria(cardapio.getCategoria());
	            return cardapioRepository.save(record);
	        });
	}
	
	public boolean delete(@PathVariable int id) {
		return cardapioRepository.findById(id)
		        .map(record -> {
		            cardapioRepository.deleteById(id);
		            return true;
		        }).orElse(false);
	}
	
}
