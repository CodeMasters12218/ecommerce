package com.example.product.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.domain.model.Product;
import com.example.product.domain.model.ProductEvent;

@Service
public class ProductPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProductCreated(Product product) {
        ProductEvent event = new ProductEvent(product.getId(), product.getName());
        rabbitTemplate.convertAndSend("product.exchange", "product.created", event);
    }
}

