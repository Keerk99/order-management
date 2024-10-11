package com.keerk99.ordermanagement.domain.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomer(
        @NotBlank(message = "Name cannot be blank")
        String first_name,

        @NotBlank(message = "Lastname cannot be blank")
        String last_name,

        String phone
) {
}
