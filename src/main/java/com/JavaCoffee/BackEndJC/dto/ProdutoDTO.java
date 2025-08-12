package com.JavaCoffee.BackEndJC.dto;

import java.util.List;

import com.JavaCoffee.BackEndJC.enums.Category;
import com.JavaCoffee.BackEndJC.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
		@JsonProperty("_id") int id,
		@NotBlank String nome,
		@NotNull double preco,
		@NotBlank @NotNull String descricao,
		@NotBlank @NotNull String imagem,
		@NotBlank @NotNull @ValueOfEnum(enumClass = Category.class) String categoria,
		List<IngredienteDTO> ingredientes) {
}
