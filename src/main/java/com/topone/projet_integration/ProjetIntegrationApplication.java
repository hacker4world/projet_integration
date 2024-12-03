package com.topone.projet_integration;

import com.topone.projet_integration.entities.Admin;
import com.topone.projet_integration.repositories.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjetIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetIntegrationApplication.class, args);
    }


    //@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        return args -> {

            String password = passwordEncoder.encode("12345678");

            Admin admin = new Admin(
                    "rami", "ben mansour", 30, "manouba", "rami@gmail.com", 111111, password, 100
            );

            adminRepository.save(admin);

            System.out.println("Admin created");

        };
    }


}
