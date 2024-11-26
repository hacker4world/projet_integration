package com.topone.projet_integration.services;

import com.topone.projet_integration.dto.AccountRequestDto;
import com.topone.projet_integration.dto.ApiResponseDto;
import com.topone.projet_integration.dto.UnacceptedEmployeesResponseDto;
import com.topone.projet_integration.dto.UnacceptedManagersResponseDto;
import com.topone.projet_integration.entities.Employee;
import com.topone.projet_integration.entities.Manager;
import com.topone.projet_integration.repositories.EmployeeRepository;
import com.topone.projet_integration.repositories.ManagerRepository;
import com.topone.projet_integration.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestsService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public AccountRequestsService(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    public List<UnacceptedEmployeesResponseDto> getPendingEmployees() {

        // get all pending employees
        List<Employee> employees = employeeRepository.findPendingEmployeeRequests();

        // created a new list to store pending employees in another format
        List<UnacceptedEmployeesResponseDto> unacceptedEmployeesResponseDto = new ArrayList<>();

        // looping through the pending employees list and filling the unacceptedEmployeesResponseDto list
        for (Employee employee : employees) {
            unacceptedEmployeesResponseDto.add(new UnacceptedEmployeesResponseDto(
                    employee.getId(),
                    employee.getName(), employee.getLastName(), employee.getEmail(), employee.getAge(), employee.getRole_employer(),
                    employee.getAdress(),  employee.getRIB(), employee.isVerified_email()
            ));
        }

        // return the list of pending employees
        return unacceptedEmployeesResponseDto;

    }

    public List<UnacceptedManagersResponseDto> getPendingManagers() {

        // get all pending managers
        List<Manager> managers = managerRepository.findPendingManagerRequests();

        // created a new list to store pending managers in another format
        List<UnacceptedManagersResponseDto> unacceptedManagersResponseDto = new ArrayList<>();

        // looping through the pending managers list and filling the unacceptedManagersResponseDto list
        for (Manager manager : managers) {
            unacceptedManagersResponseDto.add(new UnacceptedManagersResponseDto(
                    manager.getId(),
                    manager.getName(), manager.getLastName(), manager.getEmail(), manager.getAge(), manager.getGrade(),
                    manager.getAdress(),  manager.getRIB(), manager.isVerified_email()
            ));
        }

        // return the list of pending managers
        return unacceptedManagersResponseDto;
    }

    public ResponseEntity<ApiResponseDto<String>> handleEmployeeRequest(AccountRequestDto accountRequestDto) {

        // find the employee by id
        Optional<Employee> employee = getEmployeeById(accountRequestDto.getAccountId());

        // if employee is not found return a 404 error
        if (employee.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Employee with given id was not found")
        );

        Employee employeeEntity = employee.get();

        String responseMessage;

        // if the employee is accepted, set the employee object to accepted (1)
        if (accountRequestDto.isAccountAccepted()) {
            employeeEntity.setAccountAccepted(1);
            employeeEntity.setAccountRejectionReason("");
            responseMessage = "Employee account accepted";
        } else {
            // if the employee is rejected, set the employee object to rejected (-1)
            employeeEntity.setAccountAccepted(-1);
            employeeEntity.setAccountRejectionReason(accountRequestDto.getRejectionReason());
            responseMessage = "Employee account rejected";
        }

        // save the employee object changes in the database
        employeeRepository.save(employeeEntity);

        // return success response
        return ResponseEntity.status(200).body(new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), responseMessage));

    }

    public ResponseEntity<ApiResponseDto<String>> handleManagerRequest(AccountRequestDto accountRequestDto) {

        // find the manager by id
        Optional<Manager> manager = getManagerById(accountRequestDto.getAccountId());

        // if manager is not found return a 404 error
        if (manager.isEmpty()) return ResponseEntity.status(404).body(
                new ApiResponseDto<>(404, ResponseMessage.ACCOUNT_NOT_FOUND.toString(), "Manager with given id was not found")
        );

        Manager managerEntity = manager.get();
        String responseMessage;

        // if the manager is accepted, set the manager object to accepted (1)
        if (accountRequestDto.isAccountAccepted()) {
            managerEntity.setAccountAccepted(1);
            managerEntity.setAccountRejectionReason("");
            responseMessage = "Manager account accepted";
        } else {
            // if the manager is accepted, set the manager object to rejected (-1)
            managerEntity.setAccountAccepted(-1);
            managerEntity.setAccountRejectionReason(accountRequestDto.getRejectionReason());
            responseMessage = "Manager account rejected";
        }

        // save the manager object changes in the database
        managerRepository.save(managerEntity);

        // return success response
        return ResponseEntity.status(200).body(
                new ApiResponseDto<>(200, ResponseMessage.SUCCESS.toString(), responseMessage)
        );
    }

    // method to get employee by id
    private Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    // method to get manager by id
    private Optional<Manager> getManagerById(int id) {
        return managerRepository.findById(id);
    }

}
