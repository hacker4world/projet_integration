package com.topone.projet_integration.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.topone.projet_integration.DTO.EmployeeSignupDto;
import com.topone.projet_integration.DTO.LoginDto;
import com.topone.projet_integration.DTO.VerifyEmployeeDto;
import com.topone.projet_integration.Services.EmployeeService;
import com.topone.projet_integration.Services.ImageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
     @Autowired
     private EmployeeService employeeService;

     @Autowired
     private ImageService imageService;

    @PostMapping("/signup")
    public String signup(@RequestBody EmployeeSignupDto employeeSignupDto) throws MessagingException {
        return this.employeeService.add(employeeSignupDto);
    }

    @PostMapping("/verify-email")
    public String verif(@RequestBody VerifyEmployeeDto v)
    {
        return employeeService.verifyEmail(v);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginData) throws MessagingException {
        return employeeService.login(loginData.getEmail(), loginData.getPassword());
    }

    @PostMapping("/upload-image/{employeeId}")
    public String uploadImage(@RequestPart("image") MultipartFile imageFile, @PathVariable("employeeId") int employeeId ) throws IOException {
        return imageService.uploadImageForEmployee(imageFile, employeeId);
    }

}
