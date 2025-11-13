package com.example.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
