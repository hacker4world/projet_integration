package com.topone.projet_integration.dto;

public class UnacceptedManagersResponseDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private int grade;
    private String adress;
    private long rib;
    private boolean emailVerified;


    public UnacceptedManagersResponseDto(int id, String firstName, String lastName, String email, int age, int grade, String adress, long rib, boolean emailVerified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.grade = grade;
        this.adress = adress;
        this.rib = rib;
        this.emailVerified = emailVerified;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getRib() {
        return rib;
    }

    public void setRib(long rib) {
        this.rib = rib;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
