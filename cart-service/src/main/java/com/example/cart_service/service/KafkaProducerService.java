package com.example.cart_service.service;

import com.example.cart_service.event.CartEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(CartEvent event) {

        String message = "CartId: " + event.getCartId() +
                ", ProductId: " + event.getProductId() +
                ", Quantity: " + event.getQuantity();

        System.out.println("Sending message to Kafka: " + message);

        kafkaTemplate.send("cart-topic", message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Message sent successfully");
                    } else {
                        System.out.println("Kafka send failed: " + ex.getMessage());
                    }
                });
    }
}