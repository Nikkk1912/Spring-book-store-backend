package com.springbookserver.service;

import com.springbookserver.exeption_handling.exceptions.ImageNotFoundException;
import com.springbookserver.service.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageServiceImpl implements ImageService {

    @Value("${spring.image.dir}")
    private String imageDir;

    @Override
    public Resource getImage(String fileName) {
        try {
            Path imagePath = Paths.get(imageDir).resolve(fileName).normalize();
            if (!Files.exists(imagePath)) {
                throw new ImageNotFoundException("Image not found: " + fileName);
            }
            return new ByteArrayResource(Files.readAllBytes(imagePath));
        } catch (IOException e) {
            throw new ImageNotFoundException("Failed to read image: " + fileName);
        }
    }

    @Override
    public MediaType getImageMediaType(String fileName) {
        String type = fileName.split("\\.")[1].toLowerCase();
        return switch (type) {
            case "jpg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            default -> MediaType.ALL;
        };
    }
}
