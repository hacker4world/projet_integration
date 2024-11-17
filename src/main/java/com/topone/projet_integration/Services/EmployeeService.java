package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.DTO.EmployeeSignupDto;
import com.topone.projet_integration.DTO.VerifyEmployeeDto;
import com.topone.projet_integration.Entities.Admin;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.Repository.UserRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ManagerRepository managerRepository, EmailService emailService, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ApiResponseDto<String>> add(EmployeeSignupDto employeeSignupDto) throws MessagingException {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeSignupDto.getEmail());
        Optional<Manager> existingManager = managerRepository.findByEmail(employeeSignupDto.getEmail());

        if (existingEmployee.isPresent() || existingManager.isPresent()) {
            return ResponseEntity.
                    status(400).
                    body(new ApiResponseDto<>(400, ResponseMessage.EMAIL_EXISTS.toString(),
                            "Email address already in use!"
                    ));
        }

        String verificationCode = generateRandomVerificationCode();

        Employee employee = new Employee(employeeSignupDto.getName(),
                employeeSignupDto.getLastName(),
                employeeSignupDto.getAge(),
                employeeSignupDto.getAdress(),
                employeeSignupDto.getEmail(),
                employeeSignupDto.getRIB(),
                employeeSignupDto.getPassword(),
                employeeSignupDto.getRole_employer(),
                false, verificationCode,
                0);

        employeeRepository.save(employee);

        emailService.sendVerificationEmail(
                employeeSignupDto.getEmail(),
                employeeSignupDto.getName(),
                verificationCode
        );

        return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Your account has been created, an email with verification code has been sent to you"
                ));
    }

    public ResponseEntity<ApiResponseDto<String>> login(String email, String password) throws MessagingException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDto<String>(401,
                            ResponseMessage.INVALID_CREDENTIALS.toString(),
                            "Username or password incorrect"
                    ));
        }

        User userData = user.get();

        if (userData instanceof Admin) return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Successfully logged in as an admin"
                ));

        else if (userData instanceof Employee) {
            Employee employee = employeeRepository.findById(userData.getId()).get();
            if (!employee.isVerified_email()) {
                String code = generateRandomVerificationCode();
                emailService.sendVerificationEmail(employee.getEmail(), employee.getName(), code);
                employee.setEmail_verification_code(code);
                employeeRepository.save(employee);
                return ResponseEntity
                        .ok(new ApiResponseDto<String>(200,
                                ResponseMessage.EMAIL_NOT_VERIFIED.toString(),
                                "Email is not yet verified, a new code has been sent to you"
                        ));
            }
            if (employee.getAccount_accepted() == -1) return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.ACCOUNT_REJECTED.toString(),
                            "Your account has been rejected"
                    ));
            if (employee.getAccount_accepted() == 0) return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.ACCOUNT_PENDING.toString(),
                            "Your account is still awaiting approval"
                    ));

            return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.SUCCESS.toString(),
                            "Successfully logged in as an employee"
                    ));
        }

        else {
            Manager manager = managerRepository.findById(userData.getId()).get();
            if (!manager.isVerified_email()) {
                String code = generateRandomVerificationCode();
                emailService.sendVerificationEmail(manager.getEmail(), manager.getName(), code);
                manager.setEmail_verification_code(code);
                managerRepository.save(manager);
                return ResponseEntity
                        .ok(new ApiResponseDto<String>(200,
                                ResponseMessage.EMAIL_NOT_VERIFIED.toString(),
                                "Email is not yet verified, a new code has been sent to you"
                        ));
            }
            if (manager.getAccount_accepted() == -1) return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.ACCOUNT_REJECTED.toString(),
                            "Your account has been rejected"
                    ));
            if (manager.getAccount_accepted() == 0) return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.ACCOUNT_PENDING.toString(),
                            "Your account is still awaiting approval"
                    ));
            return ResponseEntity
                    .ok(new ApiResponseDto<String>(200,
                            ResponseMessage.SUCCESS.toString(),
                            "Successfully logged in as a manager"
                    ));
        }


    }

    public ResponseEntity<ApiResponseDto<String>> verifyEmail(VerifyEmployeeDto v) {
        Optional<Employee> employee = employeeRepository.findByEmail(v.getEmail());

        if (employee.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404,
                        ResponseMessage.ACCOUNT_NOT_FOUND.toString(),
                        "Could not find any account with given email address")
                );

        Employee e = employee.get();

        if (e.isVerified_email()) {
            System.out.println("email is verified");
            return ResponseEntity.badRequest().body(
                    new ApiResponseDto<>(400,
                            ResponseMessage.EMAIL_ALREADY_VERIFIED.toString(),
                            "Email is already verified")
                    );
        }

        if (!e.getEmail_verification_code().equals(v.getCode())) return ResponseEntity.status(401).body(
                new ApiResponseDto<>(401,
                        ResponseMessage.INVALID_VERIFICATION_CODE.toString(),
                        "Verification code is incorrect")
                );

        e.setVerified_email(true);
        employeeRepository.save(e);
        return ResponseEntity.ok(
                new ApiResponseDto<>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Email verification success")
                );

    }

    public String generateRandomVerificationCode() {
        Random rand = new Random();
        int randomCode = 10000 + rand.nextInt(90000);
        return String.valueOf(randomCode);
    }

}