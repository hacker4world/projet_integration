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
@DiscriminatorValue("Employee")
public class Employee extends User {
    public String role_employer;
    public int verified_email;
    public String email_verification_code;
    public int account_accepted;

    public Employee(String name,  String lastName, int age, String adress, String image, String email, long RIB, String password,String role_employer, int verified_email, String email_verification_code, int account_accepted) {
        super( name,   lastName,  age,  adress,  image,  email,  RIB,  password);
        this.role_employer = role_employer;
        this.verified_email = verified_email;
        this.email_verification_code = email_verification_code;
        this.account_accepted = account_accepted;
    }
}
