package com.example.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.services.ProductServiceManager;

@RestController
@RequestMapping("/api/products")

public class ProductController {
     @Autowired
     private ProductServiceManager serviceManager;

     @GetMapping()
     @Transactional(readOnly = true)
     public List<Product> findAllProducts() {
        return this.serviceManager.findAll();
     }

     @PostMapping()
     @Transactional
     public Product save(@RequestBody Product product) {
         return this.serviceManager.save(product);
     }

     @GetMapping("/{id}")
     @Transactional(readOnly = true)
     public Product getByIdProduct(@PathVariable Long id) {
         return this.serviceManager.findById(id);
     }

     @PutMapping("/{id}")
     @Transactional
     public ResponseEntity<?> update(@PathVariable Long id, 
                                     @RequestBody Product product) {
            Optional<Product> product1 = Optional.of(this.serviceManager.findById(id));

            if (product1.isPresent()) {
                Product newProduct = product1.get();
                newProduct.setName(product.getName());
                newProduct.setPrice(product.getPrice());

                return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.serviceManager.update(id, newProduct));
            }
            return ResponseEntity.notFound().build();
     }

     @DeleteMapping("/{id}")
     @Transactional
     public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (serviceManager.existsById(id)) {
            serviceManager.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }     
}
