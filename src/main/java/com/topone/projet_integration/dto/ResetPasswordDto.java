package com.topone.projet_integration.dto;

public class ResetPasswordDto {
    private String email;
    private String newPassword;
    private String resetCode;

    public String getEmail() {
        return email;
    }

    public String getResetCode() {
        return resetCode;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
