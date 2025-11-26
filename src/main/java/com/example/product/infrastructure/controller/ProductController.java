package com.example.product.infrastructure.controller;

import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductServicePort;
import com.example.product.infrastructure.repository.ProductEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServicePort service;

    public ProductController(ProductServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return service.findAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/{id}")
    public Product getByIdProduct(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/cheap/{price}")
    public List<ProductEntity> getCheaperThan(@PathVariable Double price) {
        return service.findByPriceLessThan(price);
    }

    @GetMapping("/contiene/{name}")
    public List<ProductEntity> getByNameContaining(@PathVariable String name) {
        return service.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/rango/{min}/{max}")
    public List<ProductEntity> getByPriceRange(@PathVariable Double min, @PathVariable Double max) {
        return service.buscarPorRangoPrecio(min, max);
    }
}
