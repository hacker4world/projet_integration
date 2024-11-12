package com.topone.projet_integration.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@DiscriminatorValue("Manager")
public class Manager extends User{
    public int grade;
    public boolean verified_email;
    public String email_verification_code;
    public int account_accepted;

    public Manager(String name, String lastName, int age, String adress, String image, String email, long RIB, String password,int grade, boolean verified_email, String email_verification_code, int account_accepted) {
        super( name, lastName,  age,  adress,  image,  email,  RIB,  password);
        this.grade = grade;
        this.verified_email = verified_email;
        this.email_verification_code = email_verification_code;
        this.account_accepted = account_accepted;
    }
}
