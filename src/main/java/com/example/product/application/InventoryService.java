package com.example.product.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.product.infrastructure.messaging.events.OrderCreatedEvent;
import com.example.product.infrastructure.messaging.events.OrderItem;
import com.example.product.infrastructure.repository.InventoryMovementEntity;
import com.example.product.infrastructure.repository.InventoryMovementRepository;
import com.example.product.infrastructure.repository.JpaProductRepository;
import com.example.product.infrastructure.repository.ProductEntity;

@Service
public class InventoryService {

    private final JpaProductRepository productRepository;
    private final InventoryMovementRepository movementRepository;

    public InventoryService(JpaProductRepository productRepository,
                            InventoryMovementRepository movementRepository) {
        this.productRepository = productRepository;
        this.movementRepository = movementRepository;
    }

    public void processOrder(OrderCreatedEvent event) {

        if (event.getItems() == null) {
            return;
        }

        for (OrderItem item : event.getItems()) {

            Long productId = Long.valueOf(item.getProductId());

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para producto " + product.getId());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product); 

            InventoryMovementEntity movement = new InventoryMovementEntity(
                    product.getId(),
                    item.getQuantity(),
                    "ORDER_CREATED",
                    event.getOrderId(),
                    LocalDateTime.now()
            );

            movementRepository.save(movement);
        }
    }
}

