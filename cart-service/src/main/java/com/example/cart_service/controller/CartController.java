package com.example.cart_service.controller;

import com.example.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    private static final Logger logger =
            LoggerFactory.getLogger(CartController.class);

    @GetMapping("/validate")
    public Mono<String> validateProduct(
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {
    	
    	logger.info("Validate API called with productId: {}, quantity: {}",
                productId, quantity);

    	return cartService.validateProduct(productId, quantity);
                
    }
}