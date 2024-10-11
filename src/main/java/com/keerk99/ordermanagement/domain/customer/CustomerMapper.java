package com.keerk99.ordermanagement.domain.customer;

import com.keerk99.ordermanagement.domain.customer.dto.CreateCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.DataListCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.UpdateCustomer;

import java.time.LocalDateTime;

public class CustomerMapper {
    public static Customer toCustomer(CreateCustomer createCustomer) {
        Customer customer = new Customer();
        customer.setFirst_name(createCustomer.first_name());
        customer.setLast_name(createCustomer.last_name());
        customer.setEmail(createCustomer.email());
        customer.setPhone(createCustomer.phone());
        customer.setAddress(createCustomer.address());
        customer.setCity(createCustomer.city());
        customer.setState(createCustomer.state());
        customer.setCountry(createCustomer.country());
        customer.setCreated_at(LocalDateTime.now());
        customer.setUpdated_at(LocalDateTime.now());

        return customer;
    }

    public static DataListCustomer toDataListCustomer(Customer customer) {
        return new DataListCustomer(customer);
    }

    public static void toUpdateCustomer(UpdateCustomer updateCustomer, Customer customer) {
        customer.setFirst_name(updateCustomer.first_name());
        customer.setLast_name(updateCustomer.last_name());
        customer.setPhone(updateCustomer.phone());
    }
}
