package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.model.entities.Cardapio;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;


@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioRepository cardapioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cardapio novoItem(@RequestBody Cardapio cardapio) {
		return cardapioRepository.save(cardapio);
	}
	@GetMapping
	public Iterable<Cardapio> obterItens(){
		return cardapioRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cardapio> findByID(@PathVariable int id) {
		return cardapioRepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping
	public Cardapio editarItem(Cardapio cardapio) {
		cardapioRepository.save(cardapio);
		return cardapio;
	}
	@PatchMapping("/{id}")
	public Cardapio editarItemParcialmente(Cardapio cardapio) {
		//var cardapioid = cardapioRepository.findAllById(cardapio);
		cardapioRepository.save(cardapio);
		return cardapio;
	}
}
