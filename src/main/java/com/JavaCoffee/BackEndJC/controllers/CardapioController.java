package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.model.entities.Cardapio;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioRepository cardapioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cardapio novoItem(@RequestBody @Valid Cardapio cardapio) {
		return cardapioRepository.save(cardapio);
	}
	@GetMapping
	public Iterable<Cardapio> obterItens(){
		return cardapioRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cardapio> findByID(@PathVariable @Positive int id) {
		return cardapioRepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		return cardapioRepository.findById(id)
		        .map(record -> {
		            cardapioRepository.deleteById(id);
		            return ResponseEntity.noContent().<Void>build();
		        }).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cardapio> editarItem(@PathVariable int id, @RequestBody @Valid Cardapio cardapio) {
	    return cardapioRepository.findById(id)
	        .map(record -> {
	            record.setNome(cardapio.getNome());
	            record.setDescricao(cardapio.getDescricao());
	            record.setPreco(cardapio.getPreco());
	            record.setImagem(cardapio.getImagem());
	            record.setCategoria(cardapio.getCategoria());
	            cardapioRepository.save(record);
	            return ResponseEntity.ok().body(record);
	        }).orElse(ResponseEntity.notFound().build());
	}

	@PatchMapping("/{id}")
	public Cardapio editarItemParcialmente(Cardapio cardapio) {
		//var cardapioid = cardapioRepository.findAllById(cardapio);
		cardapioRepository.save(cardapio);
		return cardapio;
	}
}
