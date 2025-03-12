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

import com.JavaCoffee.BackEndJC.dto.ProtutoDTO;
import com.JavaCoffee.BackEndJC.service.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/produto")
public class ProdutosController {
	
	@Autowired
	private ProdutoService produtoService;
	
	public ProdutosController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping
	public @ResponseBody List<ProtutoDTO> obterItens(){
		return produtoService.list();
	}
	
	@GetMapping("/{id}")
	public ProtutoDTO findByID(@PathVariable @Positive int id) {
		return produtoService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProtutoDTO novoItem(@RequestBody @Valid ProtutoDTO produto) {
		return produtoService.novoItem(produto);
	}

	@PutMapping("/{id}")
	public ProtutoDTO editarItem(@PathVariable int id, @RequestBody @Valid ProtutoDTO produto) {
	    return produtoService.editarItem(id, produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		produtoService.delete(id);
	}
}
