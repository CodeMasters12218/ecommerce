package com.example.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ecommerce.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
