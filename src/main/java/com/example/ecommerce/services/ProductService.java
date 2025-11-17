package com.example.ecommerce.services;

import java.util.List;

import com.example.ecommerce.entities.Product;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
}
