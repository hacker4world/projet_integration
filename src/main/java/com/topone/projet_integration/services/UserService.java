package com.topone.projet_integration.services;

import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.repositories.EmployeeRepository;
import com.topone.projet_integration.repositories.ManagerRepository;
import com.topone.projet_integration.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    public UserService(EmployeeRepository employeeRepository, ManagerRepository managerRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return
                userRepository.findAll();
    }



}
