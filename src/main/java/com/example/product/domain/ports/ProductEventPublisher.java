package com.example.product.domain.ports;

import com.example.product.domain.model.Product;

public interface ProductEventPublisher {
    void publishProductCreated(Product product);
}

