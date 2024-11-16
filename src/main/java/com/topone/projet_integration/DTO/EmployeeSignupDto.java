package com.topone.projet_integration.DTO;

public class EmployeeSignupDto {

    private String name;
    private String lastName;
    private  int age;
    private String adress;
    private String email;
    private long RIB;
    private String password;
    private String role_employer;

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
}
