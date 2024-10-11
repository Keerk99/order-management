package com.keerk99.ordermanagement.domain.customer;

import com.keerk99.ordermanagement.domain.customer.dto.CreateCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.DataListCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.UpdateCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Void> createCustomer(CreateCustomer createCustomer);
    Flux<DataListCustomer> findAllCustomers();
    Mono<DataListCustomer> findCustomerById(Long customerId);
    Mono<Void> updateCustomer(Long customerId, UpdateCustomer updateCustomer);
    Mono<Void> deleteCustomer(Long customerId);

    Mono<Boolean> existsCustomer(Long customerId);
}
