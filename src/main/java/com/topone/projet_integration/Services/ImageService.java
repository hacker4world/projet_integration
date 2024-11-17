package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ApiResponseDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public ImageService(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }


    public ResponseEntity<ApiResponseDto<String>> uploadImageForEmployee(MultipartFile imageFile, int employeeId) throws IOException {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404,
                        ResponseMessage.ACCOUNT_NOT_FOUND.toString(),
                        "Could not find employee with id " + employeeId)
                );

        byte[] imageBytes = imageFile.getBytes();

        Employee e = employee.get();

        e.setImage(imageBytes);

        employeeRepository.save(e);

        return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Employee image has been uploaded successfully"
                ));

    }

    public ResponseEntity<ApiResponseDto<String>> uploadImageForManager(MultipartFile imageFile, int managerId) throws IOException {
        Optional<Manager> manager = managerRepository.findById(managerId);

        if (manager.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404,
                        ResponseMessage.ACCOUNT_NOT_FOUND.toString(),
                        "Could not find manager with id " + manager)
                );

        byte[] imageBytes = imageFile.getBytes();

        Manager m = manager.get();

        m.setImage(imageBytes);

        managerRepository.save(m);

        return ResponseEntity
                .ok(new ApiResponseDto<String>(200,
                        ResponseMessage.SUCCESS.toString(),
                        "Employee image has been uploaded successfully"
                ));
    }


}
