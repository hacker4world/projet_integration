package com.topone.projet_integration.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("Manager")
public class Manager extends User {
    private int grade;

    public Manager(String name, String lastName, int age, String adress, String email, long RIB, String password,int grade, boolean verified_email, String email_verification_code, int account_accepted) {
        super( name, lastName,  age,  adress,  email,  RIB,  password, verified_email, email_verification_code, account_accepted, "");
        this.grade = grade;
    }

    public Manager() {

    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
