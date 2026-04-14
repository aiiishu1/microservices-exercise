package com.example.cart_service.controller;

import com.example.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/validate")
    public String validateProduct(
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {

    	 try {
    	        cartService.validateProduct(productId, quantity);
    	        return "Product is valid and stock is sufficient";
    	    } catch (RuntimeException e) {
    	        return e.getMessage(); 
    	    }
    }
}