package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ManagerSignupDto;

import com.topone.projet_integration.DTO.VerifyManagerDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.Repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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


    public String add(ManagerSignupDto managerSignupDto) throws MessagingException {
        Optional<Manager> existingManager = managerRepository.findByEmail(managerSignupDto.getEmail());
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(managerSignupDto.getEmail());

        if (existingManager.isPresent() || existingEmployee.isPresent()) return "Email already in use";

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

        return "User created, check your email";
    }

    private String generateVerificationCode() {
        Random rand = new Random();
        int randomCode = rand.nextInt(9000) + 1000;
        return String.valueOf(randomCode);
    }

    public String verifyEmail(VerifyManagerDto verificationData) {
        Optional<Manager> manager = managerRepository.findByEmail(verificationData.getEmail());

        if (manager.isEmpty()) return "Email was not found";

        Manager m = manager.get();

        if (m.isVerified_email()) return "This email is already verified";

        if (!m.getEmail_verification_code().equals(verificationData.getCode())) return "Verification code is incorrect";

        m.setVerified_email(true);
        managerRepository.save(m);

        return "email verification success";

    }

}


