package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;

@Service
public class ProductServiceManager implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(product);
    }

}
