package com.keerk99.ordermanagement.domain.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateOrder(
        @NotNull(message = "List of products can't be null")
        List<Long> productList
) {
}
