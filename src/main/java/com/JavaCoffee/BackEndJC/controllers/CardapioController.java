package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.model.entities.Cardapio;
import com.JavaCoffee.BackEndJC.model.repositories.CardapioRepository;


@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {
	
	@Autowired
	private CardapioRepository cardapioRepository;
	
	@PostMapping
	public Cardapio novoItem(@RequestParam String nome,@RequestParam Double preco,
			@RequestParam String descricao,@RequestParam String imagem) {
		Cardapio novoItem = new Cardapio(nome, preco, descricao, imagem);
		cardapioRepository.save(novoItem);
		return novoItem;
	}
	@GetMapping
	public Iterable<Cardapio> obterItens(){
		return cardapioRepository.findAll();
	}
	
	@PutMapping
	public Cardapio editarItem(Cardapio cardapio) {
		cardapioRepository.save(cardapio);
		return cardapio;
	}
}
