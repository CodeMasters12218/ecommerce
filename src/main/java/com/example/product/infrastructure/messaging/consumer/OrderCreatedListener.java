package com.example.product.infrastructure.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.product.application.InventoryService;
import com.example.product.infrastructure.messaging.config.RabbitConfig;
import com.example.product.infrastructure.messaging.events.OrderCreatedEvent;

@Service
public class OrderCreatedListener {

    private final InventoryService inventoryService;

    public OrderCreatedListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RabbitListener(queues = RabbitConfig.PRODUCT_QUEUE)
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("PRODUCT-SERVICE recibió OrderCreatedEvent: " + event.getOrderId());

        inventoryService.processOrder(event);
    }
}


