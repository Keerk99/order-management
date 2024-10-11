package com.keerk99.ordermanagement.domain.product.impl;

import com.keerk99.ordermanagement.domain.product.Product;
import com.keerk99.ordermanagement.domain.product.ProductMapper;
import com.keerk99.ordermanagement.domain.product.ProductRepository;
import com.keerk99.ordermanagement.domain.product.ProductService;
import com.keerk99.ordermanagement.domain.product.dto.CreateProduct;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;
import com.keerk99.ordermanagement.domain.product.dto.UpdateProduct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Mono<Product> createProduct(CreateProduct createProduct) {
        Product product = ProductMapper.toProduct(createProduct);
        return productRepository.save(product);
    }

    @Override
    public Flux<DataListProduct> findAllProducts() {
        return productRepository.findAll()
                .map(ProductMapper::toDataListProduct);
    }

    @Override
    public Mono<DataListProduct> findProductById(Long productId) {
        return productRepository.findById(productId)
                .map(ProductMapper::toDataListProduct)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found")
                ));
    }

    @Transactional
    @Override
    public Mono<Void> updateProduct(Long productId, UpdateProduct updateProduct) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found")
                ))
                .flatMap(existingProduct -> {
                    ProductMapper.toUpdateProduct(updateProduct, existingProduct);
                    return productRepository.save(existingProduct).then();
                });
    }

    @Override
    public Mono<Void> deleteProduct(Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found")
                ))
                .flatMap(productRepository::delete);
    }

    @Override
    public Mono<Boolean> existsProduct(Long productId) {
        return productRepository.findById(productId)
                .map(product -> true)
                .defaultIfEmpty(false);
    }
}
