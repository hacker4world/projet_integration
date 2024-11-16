package com.topone.projet_integration.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("Manager")
public class Manager extends User {
    private int grade;
    private boolean verified_email;
    private String email_verification_code;
    private int account_accepted;

    public Manager(String name, String lastName, int age, String adress, String email, long RIB, String password,int grade, boolean verified_email, String email_verification_code, int account_accepted) {
        super( name, lastName,  age,  adress,  email,  RIB,  password);
        this.grade = grade;
        this.verified_email = verified_email;
        this.email_verification_code = email_verification_code;
        this.account_accepted = account_accepted;
    }

    public Manager() {

    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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
