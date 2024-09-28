package com.springbookserver.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public interface ImageService {

    Resource getImage(String fileName);
    MediaType getImageMediaType(String fileName);
}
