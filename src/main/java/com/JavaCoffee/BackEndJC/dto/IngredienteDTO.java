package com.JavaCoffee.BackEndJC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IngredienteDTO(Long id, @NotNull @NotBlank String nome) {
	
}
