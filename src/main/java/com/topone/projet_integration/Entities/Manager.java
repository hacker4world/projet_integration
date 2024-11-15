package com.topone.projet_integration.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
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
}
