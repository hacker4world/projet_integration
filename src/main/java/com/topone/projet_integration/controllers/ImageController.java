package com.topone.projet_integration.controllers;

import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<ApiResponseDto<byte[]>> getImage(@PathVariable("userId") int userId) throws IOException {
        return imageService.getImage(userId);
    }

    @PostMapping("update")
    public ResponseEntity<ApiResponseDto<String>> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) throws IOException {
        return imageService.uploadImage(file, userId);
    }

}
