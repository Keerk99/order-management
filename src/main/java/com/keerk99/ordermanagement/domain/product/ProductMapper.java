package com.keerk99.ordermanagement.domain.product;

import com.keerk99.ordermanagement.domain.product.dto.CreateProduct;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;
import com.keerk99.ordermanagement.domain.product.dto.UpdateProduct;

import java.time.LocalDateTime;

public class ProductMapper {
    public static Product toProduct(CreateProduct createProduct) {
        Product product = new Product();
        product.setName(createProduct.name());
        product.setDescription(createProduct.description());
        product.setPrice(createProduct.price());
        product.setStock_quantity(createProduct.stock_quantity());
        product.setCreated_at(LocalDateTime.now());
        product.setUpdated_at(LocalDateTime.now());

        return product;
    }

    public static DataListProduct toDataListProduct(Product product) {
        return new DataListProduct(product);
    }

    public static void toUpdateProduct(UpdateProduct updateProduct, Product product) {
        product.setName(updateProduct.name());
        product.setDescription(updateProduct.description());
        product.setPrice(updateProduct.price());
        product.setStock_quantity(updateProduct.stock_quantity());
    }
}
