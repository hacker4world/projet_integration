package com.topone.projet_integration.dto;

public class LoginResponseDto {
    private String token;
    private int accountId;
    private String responseData;
    private String role;

    public LoginResponseDto(String token, String responseData, int accountId, String role) {
        this.token = token;
        this.responseData = responseData;
        this.accountId = accountId;
        this.role = role;
    }

    public LoginResponseDto(String responseData) {
        this.responseData = responseData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
