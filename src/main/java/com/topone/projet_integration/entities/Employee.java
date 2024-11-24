package com.topone.projet_integration.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends User {
    private String role_employer;

    public Employee(String name,  String lastName, int age, String adress, String email, long RIB, String password,String role_employer, boolean verified_email, String email_verification_code, int account_accepted) {
        super( name,   lastName,  age,  adress,  email,  RIB,  password,  verified_email,  email_verification_code,  account_accepted, "");
        this.role_employer = role_employer;
    }

    public Employee() {

    }

    public String getRole_employer() {
        return role_employer;
    }

    public void setRole_employer(String role_employer) {
        this.role_employer = role_employer;
    }
}
