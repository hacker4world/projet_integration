package com.topone.projet_integration.Services;

import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.EmployeeRepository;
import com.topone.projet_integration.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public String uploadImageForEmployee(MultipartFile imageFile, int employeeId) throws IOException {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isEmpty()) return "Could not find any employee with given id";

        byte[] imageBytes = imageFile.getBytes();

        Employee e = employee.get();

        e.setImage(imageBytes);

        employeeRepository.save(e);

        return "Employee image has been saved";

    }

    public String uploadImageForManager(MultipartFile imageFile, int managerId) throws IOException {
        Optional<Manager> manager = managerRepository.findById(managerId);

        if (manager.isEmpty()) return "Could not find any manager with given id";

        byte[] imageBytes = imageFile.getBytes();

        Manager m = manager.get();

        m.setImage(imageBytes);

        managerRepository.save(m);

        return "Manager image has been saved";
    }


}
