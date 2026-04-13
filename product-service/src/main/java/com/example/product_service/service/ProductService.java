package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Get Product by ID
    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // we'll improve in 1L
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // Delete Products by ID
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}