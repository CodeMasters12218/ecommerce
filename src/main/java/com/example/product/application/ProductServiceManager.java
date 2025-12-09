package com.example.product.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductRepositoryPort;
import com.example.product.domain.ports.ProductServicePort;
import com.example.product.infrastructure.repository.ProductEntity;
import com.example.product.infrastructure.repository.JpaProductRepository;
@Service
public class ProductServiceManager implements ProductServicePort {

    private final ProductRepositoryPort repository;
    private final JpaProductRepository jpaRepository;

    public ProductServiceManager(ProductRepositoryPort repository, JpaProductRepository jpaRepository) {
        this.repository = repository;
        this.jpaRepository = jpaRepository;
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

    @Override
    public List<ProductEntity> findByPriceLessThan(Double price) {
        return jpaRepository.findByPriceLessThan(price);
    }

    @Override
    public List<ProductEntity> findByNameContainingIgnoreCase(String name) {
        return jpaRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<ProductEntity> buscarPorRangoPrecio(Double min, Double max) {
        return jpaRepository.buscarPorRangoPrecio(min, max);
    }
}
