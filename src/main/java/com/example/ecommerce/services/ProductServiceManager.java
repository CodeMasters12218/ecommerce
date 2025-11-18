package com.example.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;

@Service
public class ProductServiceManager implements ProductService {

    private final ProductRepository repository;

    public ProductServiceManager(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product prod = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        prod.setName(product.getName());
        prod.setPrice(product.getPrice());

        return this.repository.save(prod);
    }

    @Override
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
