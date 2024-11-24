package com.topone.projet_integration.entities;

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
    private boolean verified_email;
    private String email_verification_code;
    private int accountAccepted;
    private String accountRejectionReason;
    private String passwordResetCode;

    @Lob
    private byte[] image;

    public User(String name,  String lastName, int age, String adress, String email, long RIB, String password, boolean verified_email, String email_verification_code, int account_accepted, String account_rejection_reason) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.adress = adress;
        this.email = email;
        this.RIB = RIB;
        this.password = password;
        this.verified_email = verified_email;
        this.email_verification_code = email_verification_code;
        this.accountAccepted = account_accepted;
        this.accountRejectionReason = account_rejection_reason;
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

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getEmail_verification_code() {
        return email_verification_code;
    }

    public void setEmail_verification_code(String email_verification_code) {
        this.email_verification_code = email_verification_code;
    }

    public int getAccountAccepted() {
        return accountAccepted;
    }

    public void setAccountAccepted(int account_accepted) {
        this.accountAccepted = account_accepted;
    }

    public String getAccountRejectionReason() {
        return accountRejectionReason;
    }

    public void setAccountRejectionReason(String accountRejectionReason) {
        this.accountRejectionReason = accountRejectionReason;
    }

    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }
}
