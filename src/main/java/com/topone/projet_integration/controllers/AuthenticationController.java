package com.topone.projet_integration.controllers;

import com.topone.projet_integration.dto.*;
import com.topone.projet_integration.services.AuthenticationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("signup")
    public ResponseEntity<ApiResponseDto<String>> signup(@RequestBody SignupDto signupDto) throws MessagingException {
        return authenticationService.signup(signupDto);
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponseDto<String>> login(@RequestBody LoginDto loginDto) throws MessagingException {
        return authenticationService.login(loginDto);
    }

    @PostMapping("verify-email")
    public ResponseEntity<ApiResponseDto<String>> verifyEmail(@RequestBody VerifyEmailDto verifyEmailDto) throws MessagingException {
        return authenticationService.verifyEmail(verifyEmailDto);
    }

    @PostMapping("send-password-reset-email")
    public ResponseEntity<ApiResponseDto<String>> sendPasswordResetEmail(@RequestBody SendPasswordResetEmailDto sendPasswordResetEmailDto) throws MessagingException {
        return authenticationService.sendPasswordResetEmail(sendPasswordResetEmailDto);
    }

    @PostMapping("reset-password")
    public ResponseEntity<ApiResponseDto<String>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) throws MessagingException {
        return authenticationService.resetPassword(resetPasswordDto);
    }

}
