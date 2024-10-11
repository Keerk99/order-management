package com.keerk99.ordermanagement.controller;

import com.keerk99.ordermanagement.domain.customer.CustomerService;
import com.keerk99.ordermanagement.domain.customer.dto.CreateCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.DataListCustomer;
import com.keerk99.ordermanagement.domain.customer.dto.UpdateCustomer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    public Mono<ResponseEntity<String>> createCustomer(@Valid @RequestBody CreateCustomer createCustomer) {
        return customerService.createCustomer(createCustomer)
                .then(Mono.just(
                        new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED))
                );
    }

    @GetMapping
    public Mono<ResponseEntity<List<DataListCustomer>>> getCustomers() {
      return customerService.findAllCustomers()
              .collectList()
              .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK));
    }

    @GetMapping("/{customerId}")
    public Mono<ResponseEntity<DataListCustomer>> getCustomer(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK));

    }

    @PutMapping("/{customerId}")
    public Mono<ResponseEntity<String>> updateCustomer(@PathVariable Long customerId,
                                                       @Valid @RequestBody UpdateCustomer updateCustomer) {
        return customerService.updateCustomer(customerId, updateCustomer)
                .then(Mono.just(
                        new ResponseEntity<>("Customer updated successfully", HttpStatus.OK)));
    }

    @DeleteMapping("/{customerId}")
    public Mono<ResponseEntity<String>> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId)
                .then(Mono.just(
                        new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK))
                );
    }
}
