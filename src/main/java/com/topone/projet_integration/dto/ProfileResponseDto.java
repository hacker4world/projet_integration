package com.topone.projet_integration.dto;

public class ProfileResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String address;
    private String employeeRole;
    private int managerGrade;
    private double adminExperience;

    public ProfileResponseDto(String firstName, String lastName, String email, int age, String employeeRole, int managerGrade, double adminExperience, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.employeeRole = employeeRole;
        this.managerGrade = managerGrade;
        this.adminExperience = adminExperience;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public int getManagerGrade() {
        return managerGrade;
    }

    public String getAddress() {
        return address;
    }

    public double getAdminExperience() {
        return adminExperience;
    }
}
