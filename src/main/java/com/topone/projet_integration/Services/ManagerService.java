package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.DTO.ManagerSignupDto;

import com.topone.projet_integration.DTO.VerifyManagerDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.Repository.UserRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    @Autowired
    public ManagerService(EmployeeRepository employeeRepository, ManagerRepository managerRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
        this.emailService = emailService;
    }


    public ResponseEntity<ApiResponseDto<String>> add(ManagerSignupDto managerSignupDto) throws MessagingException {
        Optional<Manager> existingManager = managerRepository.findByEmail(managerSignupDto.getEmail());
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(managerSignupDto.getEmail());

        if (existingManager.isPresent() || existingEmployee.isPresent()) return ResponseEntity
                .badRequest()
                .body(new ApiResponseDto<>(400,
                        ResponseMessage.EMAIL_EXISTS.toString(),
                        "Email address already in use!"
                ));

        String verificationCode = generateVerificationCode();

        // Create and save the new manager
        Manager manager = new Manager(
                managerSignupDto.getName(),
                managerSignupDto.getLastName(),
                managerSignupDto.getAge(),
                managerSignupDto.getAdress(),
                managerSignupDto.getEmail(),
                managerSignupDto.getRIB(),
                managerSignupDto.getPassword(),
                managerSignupDto.getGrade(),
                false,
                verificationCode,
                0
        );

        managerRepository.save(manager);

        emailService.sendVerificationEmail(managerSignupDto.getEmail(), managerSignupDto.getName(), verificationCode);

        return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Your account has been created, an email with verification code has been sent to you"
                ));
    }

    public ResponseEntity<ApiResponseDto<String>> verifyEmail(VerifyManagerDto verificationData) {
        Optional<Manager> manager = managerRepository.findByEmail(verificationData.getEmail());

        if (manager.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404,
                        ResponseMessage.ACCOUNT_NOT_FOUND.toString(),
                        "Could not find any account with given email address")
                );

        Manager m = manager.get();

        if (m.isVerified_email()) return ResponseEntity.badRequest().body(
                new ApiResponseDto<>(400,
                        ResponseMessage.EMAIL_ALREADY_VERIFIED.toString(),
                        "Email is already verified")
                );

        if (!m.getEmail_verification_code().equals(verificationData.getCode())) return ResponseEntity
                .badRequest().body(
                new ApiResponseDto<>(400,
                        ResponseMessage.INVALID_VERIFICATION_CODE.toString(),
                        "Verification code is incorrect")
                );

        m.setVerified_email(true);
        managerRepository.save(m);

        return ResponseEntity.ok(
                new ApiResponseDto<>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Email verification success")
                );

    }

    private String generateVerificationCode() {
        Random rand = new Random();
        int randomCode = rand.nextInt(9000) + 1000;
        return String.valueOf(randomCode);
    }

}


