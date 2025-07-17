package com.example.prak.service;

import com.example.prak.repository.CityRepository;
import com.example.prak.repository.ProductRepository;
import com.example.prak.repository.model.Image;
import com.example.prak.repository.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product newProduct, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() != 0) {
            image1 = imageToEntity(file1);
            image1.setPreview(true);
            newProduct.addImageToProduct(image1);
        }
        if(file2.getSize() != 0) {
            image2 = imageToEntity(file2);
            newProduct.addImageToProduct(image2);
        }
        if(file3.getSize() != 0) {
            image3 = imageToEntity(file3);
            newProduct.addImageToProduct(image3);
        }
        Product productFromDb = productRepository.save(newProduct);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(newProduct);
    }

    private Image imageToEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setSize(file.getSize());
        image.setData(file.getBytes());
        return image;
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

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
