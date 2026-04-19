package com.example.cart_service.service;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.cart_service.dto.ProductDTO;

import com.example.cart_service.event.CartEvent;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final WebClient webClient;
    private final KafkaProducerService kafkaProducerService;

    public CartService(CartRepository cartRepository,
            WebClient webClient,
            KafkaProducerService kafkaProducerService) {
this.cartRepository = cartRepository;
this.webClient = webClient;
this.kafkaProducerService = kafkaProducerService;
}
    

    // Create Cart
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get Cart by ID
    public Cart getCartById(Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElse(null);
    }

    // Get All Carts
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
   
    
    public Mono<ProductDTO> getProductFromService(Integer productId) {
        return webClient.get()
                .uri("/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDTO.class);
                
    }
    
    public Mono<String> validateProduct(Integer productId, Integer quantity) {
    	
    	return getProductFromService(productId)
    			.flatMap(product -> {
    				 if (product == null) {
    					 return Mono.error(new RuntimeException("Product not found"));
    				 }
    				 if (product.getStock() < quantity) {
    	                    return Mono.error(new RuntimeException("Insufficient stock"));
    	                }
    				// Kafka Event Publish
    	                CartEvent event = new CartEvent(1, productId, quantity);
    	                kafkaProducerService.sendEvent(event);
    	                
    	                return Mono.just("Product is valid and stock is sufficient");


    			});
        
    }
}