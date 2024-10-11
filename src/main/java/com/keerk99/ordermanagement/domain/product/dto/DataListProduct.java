package com.keerk99.ordermanagement.domain.product.dto;

import com.keerk99.ordermanagement.domain.product.Product;

import java.time.LocalDateTime;

public record DataListProduct(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock_quantity,
        LocalDateTime created_at
) {
    public DataListProduct(Product product) {
        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getStock_quantity(), product.getCreated_at());
    }
}
