package com.keerk99.ordermanagement.domain.customer.impl;

import com.keerk99.ordermanagement.domain.customer.Customer;
import com.keerk99.ordermanagement.domain.customer.CustomerMapper;
import com.keerk99.ordermanagement.domain.customer.CustomerRepository;
import com.keerk99.ordermanagement.domain.customer.CustomerService;
import com.keerk99.ordermanagement.domain.customer.dto.CreateCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.DataListCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.UpdateCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Mono<Void> createCustomer(CreateCustomer createCustomer) {
        Customer customer = CustomerMapper.toCustomer(createCustomer);
        return customerRepository.save(customer).then();
    }

    @Override
    public Flux<DataListCustomer> findAllCustomers() {
        return customerRepository.findAll()
                .map(CustomerMapper::toDataListCustomer);
    }

    @Override
    public Mono<DataListCustomer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(CustomerMapper::toDataListCustomer)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " not found")
                ));
    }

    @Transactional
    @Override
    public Mono<Void> updateCustomer(Long customerId, UpdateCustomer updateCustomer) {
        return customerRepository.findById(customerId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " not found")
                ))
                .flatMap(existingCustomer -> {
                    CustomerMapper.toUpdateCustomer(updateCustomer, existingCustomer);
                    return customerRepository.save(existingCustomer).then();
                });
    }

    @Transactional
    @Override
    public Mono<Void> deleteCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " not found")
                ))
                .flatMap(customerRepository::delete);
    }

    @Override
    public Mono<Boolean> existsCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> true)
                .defaultIfEmpty(false);
    }
}
