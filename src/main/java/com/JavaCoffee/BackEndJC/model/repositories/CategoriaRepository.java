package com.JavaCoffee.BackEndJC.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaCoffee.BackEndJC.model.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
