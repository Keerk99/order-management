package com.keerk99.ordermanagement.domain.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomer(
        @NotBlank(message = "Name can't be blank")
        String first_name,

        @NotBlank(message = "Lastname can't be blank")
        String last_name,

        @NotBlank(message = "Email can't be blank")
        @Email(message = "Invalid email format")
        String email,
        String phone,
        String address,
        String city,
        String state,
        String country
) {
}
