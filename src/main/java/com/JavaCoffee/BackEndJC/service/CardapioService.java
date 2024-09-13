package com.JavaCoffee.BackEndJC.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;
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
	
	public Cardapio findById(@PathVariable @Positive int id) {
		return cardapioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public Cardapio novoItem(@Valid Cardapio cardapio) {
		return cardapioRepository.save(cardapio);
	}
	
	public Cardapio editarItem(@Positive int id,@Valid Cardapio cardapio) {
	    return cardapioRepository.findById(id)
	        .map(record -> {
	            record.setNome(cardapio.getNome());
	            record.setDescricao(cardapio.getDescricao());
	            record.setPreco(cardapio.getPreco());
	            record.setImagem(cardapio.getImagem());
	            record.setCategoria(cardapio.getCategoria());
	            return cardapioRepository.save(record);
	        }).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@PathVariable int id) {
		cardapioRepository.delete(cardapioRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
	
}