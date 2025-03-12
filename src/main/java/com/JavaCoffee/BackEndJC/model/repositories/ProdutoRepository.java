package com.JavaCoffee.BackEndJC.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaCoffee.BackEndJC.model.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
