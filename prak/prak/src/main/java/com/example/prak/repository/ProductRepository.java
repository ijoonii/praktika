package com.example.prak.repository;

import com.example.prak.repository.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCity_Id(Long cityId);

    List<Product> findByNameContainingIgnoreCase(String name);


}
