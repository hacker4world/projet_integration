package com.topone.projet_integration.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String adress;
    private long RIB;
    private String email;
    private String password;

    @Lob
    private byte[] image;

    public User(String name,  String lastName, int age, String adress, String email, long RIB, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.adress = adress;
        this.email = email;
        this.RIB = RIB;
        this.password = password;
    }

}
