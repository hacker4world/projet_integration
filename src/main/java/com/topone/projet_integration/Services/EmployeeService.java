package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.EmployeeSignupDto;
import com.topone.projet_integration.DTO.VerifyEmployeeDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.Repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String add(EmployeeSignupDto employeeSignupDto) throws MessagingException {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeSignupDto.getEmail());
        Optional<Manager> existingManager = managerRepository.findByEmail(employeeSignupDto.getEmail());

        if (existingEmployee.isPresent() || existingManager.isPresent()) {
            return "employee existed";
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

        return "Employee added, verification email sent";
    }

    public String login(String email, String password) throws MessagingException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            return "username or password incorrect";
        }

        User userData = user.get();

        if (user.get() instanceof Employee) {
            Employee employee = employeeRepository.findById(userData.getId()).get();
            if (!employee.isVerified_email()) {
                String code = generateRandomVerificationCode();
                emailService.sendVerificationEmail(employee.getEmail(), employee.getName(), code);
                employee.setEmail_verification_code(code);
                employeeRepository.save(employee);
                return "You have not verified your email yet, a new code has been sent to your email";
            }
            if (employee.getAccount_accepted() == -1) return "Your account request has been rejected";
            if (employee.getAccount_accepted() == 0) return "Your account request is still pending";
            return "You are logged in as employee";
        }

        else if (user.get() instanceof Manager) {
            Manager manager = managerRepository.findById(userData.getId()).get();
            if (!manager.isVerified_email()) {
                String code = generateRandomVerificationCode();
                emailService.sendVerificationEmail(manager.getEmail(), manager.getName(), code);
                manager.setEmail_verification_code(code);
                managerRepository.save(manager);
                return "You have not verified your email yet, a new code has been sent to your email";
            }
            if (manager.getAccount_accepted() == -1) return "Your account request has been rejected";
            if (manager.getAccount_accepted() == 0) return "Your account request is still pending";
            return "You are logged in as manager";
        }
        else return "You are logged in as admin";

    }


    public String verifyEmail(VerifyEmployeeDto v) {
        Optional<Employee> employee = employeeRepository.findByEmail(v.getEmail());

        if (employee.isEmpty()) return "Email was not found";

        Employee e = employee.get();

        if (e.isVerified_email()) return "Email is already verified";

        if (!e.getEmail_verification_code().equals(v.getCode())) return "Incorrect verification code";

        e.setVerified_email(true);
        employeeRepository.save(e);
        return "success";

    }

    public String generateRandomVerificationCode() {
        Random rand = new Random(); // Création de l'objet Random
        int aleatoire = rand.nextInt(10000); //generer code entre
        return String.valueOf(aleatoire);
    }


}

