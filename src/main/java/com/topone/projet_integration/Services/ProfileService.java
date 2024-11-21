package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.DTO.ModifyProfileDto;
import com.topone.projet_integration.Entities.Admin;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Entities.User;
import com.topone.projet_integration.Repository.AdminRepository;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ProfileService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public ProfileService(EmployeeRepository employeeRepository, ManagerRepository managerRepository, AdminRepository adminRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<ApiResponseDto> modifyProfile(ModifyProfileDto modifyProfileDto) {
        Optional<Employee> employee = employeeRepository.findById(modifyProfileDto.getUserId());
        Optional<Manager> manager = managerRepository.findById(modifyProfileDto.getUserId());
        Optional<Admin> admin = adminRepository.findById(modifyProfileDto.getUserId());

        if (employee.isEmpty() && manager.isEmpty() && admin.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponseDto(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Did not find any account with given id")
            );
        }
        else if (employee.isPresent()) {
            Employee employee1 = employee.get();
            employee1.setAdress(modifyProfileDto.getAddress());
            employee1.setAge(modifyProfileDto.getAge());
            employeeRepository.save(employee1);
        }
        else if (manager.isPresent()) {
            Manager manager1 = manager.get();
            manager1.setAdress(modifyProfileDto.getAddress());
            manager1.setAge(modifyProfileDto.getAge());
            managerRepository.save(manager1);

        }  else {
            Admin admin1 = admin.get();
            admin1.setAdress(modifyProfileDto.getAddress());
            admin1.setAge(modifyProfileDto.getAge());
            adminRepository.save(admin1);
        }

        return ResponseEntity.status(200).body(
                new ApiResponseDto(200, ResponseMessage.SUCCESS.toString(), "Profile updated.")
        );

    }


}
