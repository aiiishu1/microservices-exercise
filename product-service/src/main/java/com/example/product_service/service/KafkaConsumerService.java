package com.example.product_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "cart-topic", groupId = "product-group")
    public void consume(String message) {

        System.out.println("Received message from Kafka: " + message);
    }
}