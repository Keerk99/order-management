package com.keerk99.ordermanagement.domain.customer.dto;

import com.keerk99.ordermanagement.domain.customer.Customer;

import java.time.LocalDateTime;

public record DataListCustomer(
        Long id,
        String first_name,
        String last_name,
        String email,
        String phone,
        String address,
        String city,
        String state,
        String country,
        LocalDateTime created_at
) {
    public DataListCustomer(Customer customer) {
        this(customer.getId(), customer.getFirst_name(), customer.getLast_name(), customer.getEmail(),
                customer.getPhone(), customer.getAddress(), customer.getCity(), customer.getState(),
                customer.getCountry(), customer.getCreated_at());
    }
}
