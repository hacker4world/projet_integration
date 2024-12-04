package com.topone.projet_integration.services;

import com.topone.projet_integration.dto.updatedUser;
import com.topone.projet_integration.entities.User;
import com.topone.projet_integration.repositories.EmployeeRepository;
import com.topone.projet_integration.repositories.ManagerRepository;
import com.topone.projet_integration.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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




        public String deleteUser(int id) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                userRepository.deleteById(id);
                return "supprimer avec succe";
            }
            return "user not fount";
        }




        public String updateUser(int id, updatedUser updatedUser) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                existingUser.setName(updatedUser.getName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setAge(updatedUser.getAge());
                existingUser.setAdress(updatedUser.getAdress());
                existingUser.setEmail(updatedUser.getEmail());






                userRepository.save(existingUser);
                return "modifier avec succe";
            }
                return "User not found with ID: " + id;

        }
    }




