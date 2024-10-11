package com.keerk99.ordermanagement.domain.product;

import com.keerk99.ordermanagement.domain.product.dto.CreateProduct;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;
import com.keerk99.ordermanagement.domain.product.dto.UpdateProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(CreateProduct createProduct);
    Flux<DataListProduct> findAllProducts();
    Mono<DataListProduct> findProductById(Long productId);
    Mono<Void> updateProduct(Long productId, UpdateProduct updateProduct);
    Mono<Void> deleteProduct(Long productId);
    Mono<Boolean> existsProduct(Long productId);
}
