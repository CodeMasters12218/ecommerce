package com.example.product.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductRepositoryPort;
import com.example.product.domain.ports.ProductServicePort;
@Service
public class ProductServiceManager implements ProductServicePort {

    private final ProductRepositoryPort repository;

    public ProductServiceManager(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product prod = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        prod.setName(product.getName());
        prod.setPrice(product.getPrice());

        return repository.save(prod);
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
