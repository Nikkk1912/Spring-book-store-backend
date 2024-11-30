package com.springbookserver.service;

import com.springbookserver.service.interfaces.ImageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public Resource getImage(String fileName) {
        String imagePath = "static/" + fileName;
        Resource resource = new ClassPathResource(imagePath);

        if (!resource.exists()) {
            String blankImagePath = "static/" + "blank.jpg";
            return new ClassPathResource(blankImagePath);
        }
        return resource;
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
