package com.topone.projet_integration.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Admin")
public class Admin extends User{
    public int experience;



    public Admin(String name, String lastName, int age, String adress, String image, String email, long RIB, String password, int experience) {
        super(name, lastName, age, adress, image, email, RIB, password);
        this.experience = experience;
    }
}
