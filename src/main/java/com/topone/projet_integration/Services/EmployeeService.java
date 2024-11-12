package com.topone.projet_integration.Services;


import com.topone.projet_integration.DTO.EmployeeDto;
import com.topone.projet_integration.Entities.Employee;
import com.topone.projet_integration.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public String add(EmployeeDto employeeDto) {

        Employee existe = employeeRepository.findByEmail(employeeDto.email);
        if (existe == null) {
            Employee employee = new Employee(employeeDto.name, employeeDto.lastName, employeeDto.age, employeeDto.adress, employeeDto.image, employeeDto.email, employeeDto.RIB, employeeDto.password, employeeDto.role_employer, false, null, 0);

            employeeRepository.save(employee);

            return "created";
        } else {
            return "existed";
        }

    }
}
