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
@DiscriminatorValue("Admin")
public class Admin extends User{
    private int experience;

    public Admin(String name, String lastName, int age, String adress, String email, long RIB, String password, int experience) {
        super(name, lastName, age, adress, email, RIB, password);
        this.experience = experience;
    }
}
