package com.topone.projet_integration.DTO;

import lombok.Getter;

@Getter
public class EmployeeSignupDto {

    private String name;
    private String lastName;
    private  int age;
    private String adress;
    private String email;
    private long RIB;
    private String password;
    private String role_employer;

}
