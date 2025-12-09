package com.example.product;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.product.application.ProductServiceManager;
import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductRepositoryPort;
import com.example.product.infrastructure.repository.JpaProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceManagerTest {

    @Mock
    private ProductRepositoryPort repository;

    @Mock
    private JpaProductRepository jpaRepository;

    @InjectMocks
    private ProductServiceManager service;

    @Test
    void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Laptop");
        existingProduct.setPrice(1000.0);

        Product newProductData = new Product();
        newProductData.setName("Gaming Laptop");
        newProductData.setPrice(1200.0);

        when(repository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Product updatedProduct = service.update(1L, newProductData);

        // Assert
        assertEquals("Gaming Laptop", updatedProduct.getName());
        assertEquals(1200.0, updatedProduct.getPrice());
        verify(repository).findById(1L);
        verify(repository).save(existingProduct);
    }
}

