package com.example.prak.service;

import com.example.prak.repository.ProductRepository;
import com.example.prak.repository.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    public final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
