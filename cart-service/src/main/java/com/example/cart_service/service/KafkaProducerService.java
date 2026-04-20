package com.example.cart_service.service;

import com.example.cart_service.event.CartEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(KafkaProducerService.class);

    public void sendEvent(CartEvent event) {

        String message = "CartId: " + event.getCartId() +
                ", ProductId: " + event.getProductId() +
                ", Quantity: " + event.getQuantity();

        logger.info("Sending Kafka event: {}", message);
        
        kafkaTemplate.send("cart-topic", message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                    	logger.info("Kafka message sent successfully");
                    } else {
                        logger.error("Kafka send failed: {}", ex.getMessage());
                    }
                });
    }
}