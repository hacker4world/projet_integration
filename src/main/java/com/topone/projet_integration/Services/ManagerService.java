package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ManagerDto;

import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    public String add(ManagerDto managerDto) {
        Manager existe=managerRepository.findByEmail(managerDto.email);
        if (existe == null) {
            Manager manager = new Manager(managerDto.name, managerDto.lastName, managerDto.age, managerDto.adress, managerDto.image, managerDto.email, managerDto.RIB, managerDto.password, managerDto.grade, false, null, 0);

            managerRepository.save(manager);

            return "created";
        } else {
            return "existed";
        }
    }
}


