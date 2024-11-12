package com.topone.projet_integration.Controllers;


import com.topone.projet_integration.DTO.EmployeeDto;
import com.topone.projet_integration.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
 @Autowired
 private EmployeeService employeeService;
    @PostMapping("/add")
    public String add(@RequestBody EmployeeDto employeeDto)
    {
        return employeeService.add(employeeDto);

    }
}
