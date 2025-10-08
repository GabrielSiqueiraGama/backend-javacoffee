package com.JavaCoffee.BackEndJC.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.JavaCoffee.BackEndJC.dto.ProductPageDTO;
import com.JavaCoffee.BackEndJC.dto.ProdutoDTO;
import com.JavaCoffee.BackEndJC.dto.mapper.ProdutoMapper;
import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;
import com.JavaCoffee.BackEndJC.model.entities.Produto;
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
	
	public ProductPageDTO list(int pages, int pageSizes){
		Page<Produto> page = produtoRepository.findAll(PageRequest.of(pages, pageSizes));
		List<ProdutoDTO> products = page.get().map(produtoMapper::toDTO)
				.collect(Collectors.toList());
		return new ProductPageDTO(products, page.getTotalElements(), page.getTotalPages());

	}
	/*
	public List<ProdutoDTO> list(){
		return produtoRepository.findAll()
				.stream()
				.map(produtoMapper::toDTO)
				.collect(Collectors.toList());
	}*/
	
	public ProdutoDTO findById(@Positive int id) {
		return produtoRepository.findById(id).map(produtoMapper::toDTO).orElseThrow(() -> new ProductNotFoundException());
	}
	
	public ProdutoDTO novoItem(@Valid ProdutoDTO produto) {
		return produtoMapper.toDTO(produtoRepository.save(produtoMapper.toEntity(produto)));
	}
	
	public ProdutoDTO editarItem(@Positive int id,@Valid ProdutoDTO produtoDTO) {
	    return produtoRepository.findById(id)
	        .map(recordFound -> {
	        	Produto produto = produtoMapper.toEntity(produtoDTO);
	            recordFound.setNome(produtoDTO.nome());
	            recordFound.setDescricao(produtoDTO.descricao());
	            recordFound.setPreco(produtoDTO.preco());
	            recordFound.setImagem(produtoDTO.imagem());
	            recordFound.setCategoria(this.produtoMapper.convertCategoryValue(produtoDTO.categoria()));
	            recordFound.getIngredientes().clear();
	            produto.getIngredientes().forEach(recordFound.getIngredientes()::add);
	            return produtoMapper.toDTO(produtoRepository.save(recordFound));
	        }).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(int id) {
		produtoRepository.delete(produtoRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id)));
	}
	
}
