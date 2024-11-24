package com.topone.projet_integration.dto;

public class UnacceptedEmployeesResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String employeeRole;
    private String adress;
    private long rib;
    private boolean emailVerified;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getRib() {
        return rib;
    }

    public void setRib(int rib) {
        this.rib = rib;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public UnacceptedEmployeesResponseDto(String firstName, String lastName, String email, int age, String employeeRole, String adress, long rib, boolean emailVerified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.employeeRole = employeeRole;
        this.adress = adress;
        this.rib = rib;
        this.emailVerified = emailVerified;
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

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}