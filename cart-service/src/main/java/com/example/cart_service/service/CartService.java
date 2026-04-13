package com.example.cart_service.service;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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
}