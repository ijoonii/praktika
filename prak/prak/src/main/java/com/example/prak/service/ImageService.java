package com.example.prak.service;

import com.example.prak.repository.ImageRepository;
import com.example.prak.repository.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
