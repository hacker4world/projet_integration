package com.topone.projet_integration.Entities;

import jakarta.persistence.*;

@Entity
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

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getRIB() {
        return RIB;
    }

    public void setRIB(long RIB) {
        this.RIB = RIB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
