package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.EmployeeDto;
import com.topone.projet_integration.DTO.verifEmployeeDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Servicemail.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    public String add(EmployeeDto employeeDto) throws MessagingException {
        Optional<Employee> existe = employeeRepository.findByEmail(employeeDto.email);
        if (existe.isPresent()) {
            return "employee existed";
        }
        Random rand = new Random(); // Création de l'objet Random
        int aleatoire = rand.nextInt(10000); //generer code entre

        // Conversion en String
        String aleatoireString = String.valueOf(aleatoire);
        Employee employee = new Employee(employeeDto.name, employeeDto.lastName, employeeDto.age, employeeDto.adress, employeeDto.image, employeeDto.email, employeeDto.RIB, employeeDto.password, employeeDto.role_employer, 0, aleatoireString, 0);
        employeeRepository.save(employee);

        emailService.sendVerificationEmail(employeeDto.email, employeeDto.name, aleatoireString);

        return "Employee added, verification email sent";
    }
//n
    public String verif(verifEmployeeDto v) {
        Optional<Employee> existe = employeeRepository.findByEmail(v.email);
        if (existe.isPresent()) {
            if(v.code.equals(existe.get().email_verification_code)) {
                existe.get().setVerified_email(1);
                return "success";
            }

        }
        if (existe.isEmpty()) {
            return "not found";
        }

            return "incorrect";

    }
}

