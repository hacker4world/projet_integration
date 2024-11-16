package com.topone.projet_integration.DTO;

import org.springframework.web.multipart.MultipartFile;

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

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAdress() {
        return adress;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public long getRIB() {
        return RIB;
    }

    public String getPassword() {
        return password;
    }

    public int getGrade() {
        return grade;
    }
}
