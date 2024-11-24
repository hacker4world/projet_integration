package com.topone.projet_integration.dto;

public class UpdateProfileDto {
    private String firstName;
    private String lastName;
    private int age;
    private String adress;
    private int userId;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getUserId() {
        return userId;
    }

    public String getAdress() {
        return adress;
    }
}
