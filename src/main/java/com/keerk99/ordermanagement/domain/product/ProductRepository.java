package com.keerk99.ordermanagement.domain.product;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductRepository extends R2dbcRepository<Product, Long> {

}
