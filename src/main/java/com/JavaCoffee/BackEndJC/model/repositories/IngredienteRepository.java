package com.JavaCoffee.BackEndJC.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaCoffee.BackEndJC.model.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
	Optional<Ingrediente> findByNome(String nome);
}
