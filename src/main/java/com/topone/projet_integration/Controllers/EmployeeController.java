package com.topone.projet_integration.Controllers;



import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.DTO.EmployeeSignupDto;
import com.topone.projet_integration.DTO.LoginDto;
import com.topone.projet_integration.DTO.VerifyEmployeeDto;
import com.topone.projet_integration.Services.EmployeeService;
import com.topone.projet_integration.Services.ImageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
     private final EmployeeService employeeService;

     private final ImageService imageService;

      @Autowired
     public EmployeeController(EmployeeService employeeService, ImageService imageService) {
         this.employeeService = employeeService;
         this.imageService = imageService;
     }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<String>> signup(@RequestBody EmployeeSignupDto employeeSignupDto) throws MessagingException {
        return this.employeeService.add(employeeSignupDto);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponseDto<String>> verifyEmail(@RequestBody VerifyEmployeeDto v)
    {
        return employeeService.verifyEmail(v);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<String>> login(@RequestBody LoginDto loginData) throws MessagingException {
        return employeeService.login(loginData.getEmail(), loginData.getPassword());
    }

    @PostMapping("/upload-image/{employeeId}")
    public ResponseEntity<ApiResponseDto<String>> uploadImage(@RequestPart("image") MultipartFile imageFile, @PathVariable("employeeId") int employeeId ) throws IOException {
        return imageService.uploadImageForEmployee(imageFile, employeeId);
    }

}
