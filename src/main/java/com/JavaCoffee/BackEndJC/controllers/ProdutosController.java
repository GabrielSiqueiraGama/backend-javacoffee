package com.JavaCoffee.BackEndJC.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.JavaCoffee.BackEndJC.dto.ProductPageDTO;
import com.JavaCoffee.BackEndJC.dto.ProdutoDTO;
import com.JavaCoffee.BackEndJC.service.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

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
	public ProductPageDTO obterItens(@RequestParam(defaultValue = "0") @PositiveOrZero int pages, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize){
		return produtoService.list(pages, pageSize);
	}
	
	/*@GetMapping
	public List<ProdutoDTO> obterItens(){
		return produtoService.list();
	}*/
	
	@GetMapping("/{id}")
	public ProdutoDTO findByID(@PathVariable @Positive int id) {
		return produtoService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO novoItem(@RequestBody @Valid ProdutoDTO produto) {
		return produtoService.novoItem(produto);
	}

	@PutMapping("/{id}")
	public ProdutoDTO editarItem(@PathVariable int id, @RequestBody @Valid ProdutoDTO produto) {
	    return produtoService.editarItem(id, produto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		produtoService.delete(id);
	}
}
