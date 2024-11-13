package com.topone.projet_integration.Controllers;


import com.topone.projet_integration.DTO.EmployeeDto;
import com.topone.projet_integration.DTO.verifEmployeeDto;
import com.topone.projet_integration.Services.EmployeeService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
 @Autowired
 private EmployeeService employeeService;
    @PostMapping("/add")
    public String add(@RequestBody EmployeeDto employeeDto) throws MessagingException {
        return employeeService.add(employeeDto);

    }
    @PostMapping("/afficher")
    public String verif(@RequestBody verifEmployeeDto v)
    {
        return employeeService.verif(v);
    }
}
