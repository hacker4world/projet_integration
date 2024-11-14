package com.topone.projet_integration.Controllers;

import com.topone.projet_integration.DTO.ManagerSignupDto;
import com.topone.projet_integration.DTO.VerifyManagerDto;
import com.topone.projet_integration.Services.ImageService;
import com.topone.projet_integration.Services.ManagerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Manager")
public class ManagerController {

    private final ManagerService managerService;
    private final ImageService imageService;

    @Autowired
    public ManagerController(ManagerService managerService, ImageService imageService) {
        this.managerService = managerService;
        this.imageService = imageService;
    }

    @PostMapping("/signup")
    public String add(@RequestBody ManagerSignupDto managerSignupDto) throws MessagingException {
        return managerService.add(managerSignupDto);
    }

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestBody VerifyManagerDto verificationData) {
        return managerService.verifyEmail(verificationData);
    }

    @PostMapping("/upload-image/{managerId}")
    public String uploadImage(@RequestPart("image") MultipartFile imageFile, @PathVariable("managerId") int managerId) throws IOException {
        return imageService.uploadImageForManager(imageFile, managerId);
    }
}
