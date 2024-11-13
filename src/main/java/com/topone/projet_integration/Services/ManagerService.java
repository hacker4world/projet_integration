package com.topone.projet_integration.Services;

import com.topone.projet_integration.DTO.ManagerDto;

import com.topone.projet_integration.Entities.Manager;
import com.topone.projet_integration.Repository.ManagerRepository;
import com.topone.projet_integration.Servicemail.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    private EmailService emailService;

    public String add(ManagerDto managerDto) throws MessagingException {
        Manager existe=managerRepository.findByEmail(managerDto.email);
        if (existe == null) {
            Random rand = new Random(); // Création de l'objet Random
            int aleatoire = rand.nextInt(10000); //generer code entre

            // Conversion en String
            String aleatoireString = String.valueOf(aleatoire);
            Manager manager = new Manager(managerDto.name, managerDto.lastName, managerDto.age, managerDto.adress, managerDto.image, managerDto.email, managerDto.RIB, managerDto.password, managerDto.grade, false, aleatoireString, 0);

            managerRepository.save(manager);
            emailService.sendVerificationEmail(managerDto.email,managerDto.name,aleatoireString);

            return "User created,check ur email";
        } else {
            return "existed";
        }
    }
}


