package com.JavaCoffee.BackEndJC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.dto.CardapioDTO;
import com.JavaCoffee.BackEndJC.service.CardapioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioService cardapioService;
	
	public CardapioController(CardapioService cardapioService) {
		this.cardapioService = cardapioService;
	}
	
	@GetMapping
	public @ResponseBody List<CardapioDTO> obterItens(){
		return cardapioService.list();
	}
	
	@GetMapping("/{id}")
	public CardapioDTO findByID(@PathVariable @Positive int id) {
		return cardapioService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CardapioDTO novoItem(@RequestBody @Valid CardapioDTO cardapio) {
		return cardapioService.novoItem(cardapio);
	}

	@PutMapping("/{id}")
	public CardapioDTO editarItem(@PathVariable int id, @RequestBody @Valid CardapioDTO cardapio) {
	    return cardapioService.editarItem(id, cardapio);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		cardapioService.delete(id);
	}
}
