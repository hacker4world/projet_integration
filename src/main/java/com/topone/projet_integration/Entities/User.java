package com.topone.projet_integration.Entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity

@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
    public String name;
    public String lastName;
    public  int age;
    public String adress;
    public String image;
    public String email;
    public long RIB;
    public String password;

    public User(String name,  String lastName, int age, String adress, String image, String email, long RIB, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.adress = adress;
        this.image = image;
        this.email = email;
        this.RIB = RIB;
        this.password = password;
    }
}
