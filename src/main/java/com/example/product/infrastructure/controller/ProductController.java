package com.example.product.infrastructure.controller;

import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductServicePort;
import com.example.product.infrastructure.repository.ProductEntity;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductServicePort service;

    public ProductController(ProductServicePort service) { this.service = service; }

    @Operation(summary = "Get all products", description = "Returns a list of all products.")
    @GetMapping
    public List<Product> findAllProducts() { return service.findAll(); }

    @Operation(summary = "Create product", description = "Saves a new product.")
    @PostMapping
    public Product save(@RequestBody Product product) { return service.save(product); }

    @Operation(summary = "Find product by ID", description = "Returns a product by its unique identifier.")
    @GetMapping("/{id}")
    public Product getByIdProduct(@PathVariable Long id) { return service.findById(id); }

    @Operation(summary = "Update product", description = "Updates an existing product by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(service.update(id, product));
    }

    @Operation(summary = "Delete product", description = "Deletes a product by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get products cheaper than price", description = "Returns products with price less than given value.")
    @GetMapping("/cheap/{price}")
    public List<ProductEntity> getCheaperThan(@PathVariable Double price) {
        return service.findByPriceLessThan(price);
    }

    @Operation(summary = "Get products by name containing", description = "Returns products whose name contains given fragment.")
    @GetMapping("/contiene/{name}")
    public List<ProductEntity> getByNameContaining(@PathVariable String name) {
        return service.findByNameContainingIgnoreCase(name);
    }

    @Operation(summary = "Get products by price range", description = "Returns products within given price range.")
    @GetMapping("/rango/{min}/{max}")
    public List<ProductEntity> getByPriceRange(@PathVariable Double min, @PathVariable Double max) {
        return service.buscarPorRangoPrecio(min, max);
    }
}
