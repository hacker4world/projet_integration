package com.topone.projet_integration.Entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends User {
    private String role_employer;
    private boolean verified_email;
    private String email_verification_code;
    private int account_accepted;

    public Employee(String name,  String lastName, int age, String adress, String email, long RIB, String password,String role_employer, boolean verified_email, String email_verification_code, int account_accepted) {
        super( name,   lastName,  age,  adress,  email,  RIB,  password);
        this.role_employer = role_employer;
        this.verified_email = verified_email;
        this.email_verification_code = email_verification_code;
        this.account_accepted = account_accepted;
    }

    public Employee() {

    }

    public String getRole_employer() {
        return role_employer;
    }

    public void setRole_employer(String role_employer) {
        this.role_employer = role_employer;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getEmail_verification_code() {
        return email_verification_code;
    }

    public void setEmail_verification_code(String email_verification_code) {
        this.email_verification_code = email_verification_code;
    }

    public int getAccount_accepted() {
        return account_accepted;
    }

    public void setAccount_accepted(int account_accepted) {
        this.account_accepted = account_accepted;
    }
}
