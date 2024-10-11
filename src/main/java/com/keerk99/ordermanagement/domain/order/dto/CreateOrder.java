package com.keerk99.ordermanagement.domain.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrder(
        @NotNull(message = "customerId can't be blank")
        Long customerId,
        @NotNull(message = "List of products IDs can't be blank")
        List<Long> productList
) {
}
