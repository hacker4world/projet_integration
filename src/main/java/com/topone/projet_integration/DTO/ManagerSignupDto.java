package com.topone.projet_integration.DTO;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ManagerSignupDto {
    private String name;
    private String lastName;
    private  int age;
    private String adress;
    private MultipartFile image;
    private String email;
    private long RIB;
    private String password;

  private int grade;
}
