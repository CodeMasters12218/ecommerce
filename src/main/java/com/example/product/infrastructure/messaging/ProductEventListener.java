package com.example.product.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.product.domain.model.ProductEvent;

@Service
public class ProductEventListener {

    @RabbitListener(queues = "product.created.queue")
    public void consumeProductCreated(ProductEvent event) {
        System.out.println("Recibido producto: " + event.getName());
        // Aquí actualizas tu modelo User si lo necesitas
    }
}

