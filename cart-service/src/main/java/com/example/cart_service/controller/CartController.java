package com.example.cart_service.controller;

import com.example.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/validate")
    public Mono<String> validateProduct(
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {

    	return cartService.validateProduct(productId, quantity);
                
    }
}