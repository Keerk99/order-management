package com.keerk99.ordermanagement.controller;

import com.keerk99.ordermanagement.domain.product.ProductService;
import com.keerk99.ordermanagement.domain.product.dto.CreateProduct;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;
import com.keerk99.ordermanagement.domain.product.dto.UpdateProduct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<String>> createProduct(@Valid @RequestBody CreateProduct createProduct) {
        return productService.createProduct(createProduct)
                .then(Mono.just(
                        new ResponseEntity<>("Product created successfully", HttpStatus.CREATED))
                );
    }

    @GetMapping
    public Mono<ResponseEntity<List<DataListProduct>>> getProducts() {
        return productService.findAllProducts()
                .collectList()
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK));
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<DataListProduct>> getProduct(@PathVariable Long productId) {
        return productService.findProductById(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK));
    }

    @PutMapping("/{productId}")
    public Mono<ResponseEntity<String>> updateProduct(@PathVariable Long productId,
                                                      @Valid @RequestBody UpdateProduct updateProduct) {
        return productService.updateProduct(productId, updateProduct)
                .then(Mono.just(
                        new ResponseEntity<>("Product updated successfully", HttpStatus.OK)
                ));
    }

    @DeleteMapping("/{productId}")
    public Mono<ResponseEntity<String>> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId)
                .then(Mono.just(
                        new ResponseEntity<>("Product deleted successfully", HttpStatus.OK)
                ));
    }
}
