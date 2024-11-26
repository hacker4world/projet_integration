package com.topone.projet_integration.dto;

public class SignupDto {
    private String name;
    private String lastName;
    private  int age;
    private String adress;
    private String email;
    private long RIB;
    private String password;
    private String role_employer;
    private int grade;
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

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

    public String getEmail() {
        return email;
    }

    public long getRIB() {
        return RIB;
    }

    public String getPassword() {
        return password;
    }

    public String getRole_employer() {
        return role_employer;
    }

    public int getGrade() {
        return grade;
    }
}
