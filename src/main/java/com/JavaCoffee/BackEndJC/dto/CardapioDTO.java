package com.JavaCoffee.BackEndJC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardapioDTO(
		@JsonProperty("_id") int id,
		@NotBlank String nome,
		@NotNull double preco,
		@NotBlank @NotNull String descricao,
		@NotBlank @NotNull String imagem,
		@NotBlank @NotNull String categoria) {

}
