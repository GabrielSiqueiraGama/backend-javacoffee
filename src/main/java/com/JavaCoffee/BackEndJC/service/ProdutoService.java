package com.JavaCoffee.BackEndJC.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.JavaCoffee.BackEndJC.dto.ProtutoDTO;
import com.JavaCoffee.BackEndJC.dto.mapper.ProdutoMapper;
import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;
import com.JavaCoffee.BackEndJC.model.repositories.ProdutoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;
	private ProdutoMapper produtoMapper;
	
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
		this.produtoRepository = produtoRepository;
		this.produtoMapper = produtoMapper;
	}
	
	public List<ProtutoDTO> list(){
		return produtoRepository.findAll()
				.stream()
				.map(produtoMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public ProtutoDTO findById(@Positive int id) {
		return produtoRepository.findById(id).map(produtoMapper::toDTO).orElseThrow(() -> new ProductNotFoundException());
	}
	
	public ProtutoDTO novoItem(@Valid ProtutoDTO produto) {
		return produtoMapper.toDTO(produtoRepository.save(produtoMapper.toEntity(produto)));
	}
	
	public ProtutoDTO editarItem(@Positive int id,@Valid ProtutoDTO produto) {
	    return produtoRepository.findById(id)
	        .map(recordFound -> {
	            recordFound.setNome(produto.nome());
	            recordFound.setDescricao(produto.descricao());
	            recordFound.setPreco(produto.preco());
	            recordFound.setImagem(produto.imagem());
	            recordFound.setCategoria(this.produtoMapper.convertCategoryValue(produto.categoria()));
	            return produtoMapper.toDTO(produtoRepository.save(recordFound));
	        }).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(int id) {
		produtoRepository.delete(produtoRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
	
}
