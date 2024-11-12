package com.topone.projet_integration.Controllers;

import com.topone.projet_integration.DTO.ManagerDto;
import com.topone.projet_integration.Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Manager")
public class ManagerController {
 @Autowired
    ManagerService managerService;
    @PostMapping("/add")
    public String add(@RequestBody ManagerDto managerDto){
        return managerService.add(managerDto);
    }
}
