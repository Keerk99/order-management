package com.keerk99.ordermanagement.domain.product.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProduct(
        @NotBlank(message = "Name can't be blank")
        String name,
        @NotBlank(message = "Name can't be blank")
        String description,
        Double price,
        Integer stock_quantity
) {
}
