package com.topone.projet_integration.dto;

public class AccountRequestDto {
    private int accountId;
    private boolean accountAccepted;
    private String rejectionReason;

    public int getAccountId() {
        return accountId;
    }

    public boolean isAccountAccepted() {
        return accountAccepted;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
