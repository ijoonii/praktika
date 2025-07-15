package com.example.prak.controller;

import com.example.prak.repository.model.Image;
import com.example.prak.service.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        return ResponseEntity.ok()
                .header("fileName", imageService.getImageById(id).getOriginalFileName())
                .contentType(MediaType.valueOf(imageService.getImageById(id).getType()))
                .contentLength(imageService.getImageById(id).getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageService.getImageById(id).getData())));
    }
}
