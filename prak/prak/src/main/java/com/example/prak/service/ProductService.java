package com.example.prak.service;

import com.example.prak.repository.CityRepository;
import com.example.prak.repository.ProductRepository;
import com.example.prak.repository.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


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

    public List<Product> getAllByName(String name) {
        if (name != null) return productRepository.findByNameContainingIgnoreCase(name);
        else return (List<Product>) getAll();
    }

    public List<Product> getAllByCity(Long cityId) {
        return productRepository.findAllByCity_Id(cityId);
    }

    public List<Product> getAllByCategory(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }
}
