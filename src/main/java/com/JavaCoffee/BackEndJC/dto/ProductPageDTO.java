package com.JavaCoffee.BackEndJC.dto;

import java.util.List;

public record ProductPageDTO(List<ProdutoDTO> products, long totalElements, int totalPages) {


}
