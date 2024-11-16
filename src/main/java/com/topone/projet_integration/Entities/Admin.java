package com.topone.projet_integration.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User{
    private int experience;

    public Admin(String name, String lastName, int age, String adress, String email, long RIB, String password, int experience) {
        super(name, lastName, age, adress, email, RIB, password);
        this.experience = experience;
    }

    public Admin() {

    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
