package com.example.product.infrastructure.repository;

import com.example.product.domain.model.Product;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getPrice());
    }
}
