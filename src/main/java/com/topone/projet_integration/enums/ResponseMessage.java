package com.topone.projet_integration.enums;

public enum ResponseMessage {
    EMAIL_EXISTS("EMAIL_EXISTS"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS"),
    EMAIL_NOT_VERIFIED("EMAIL_NOT_VERIFIED"),
    ACCOUNT_REJECTED("ACCOUNT_REJECTED"),
    ACCOUNT_PENDING("ACCOUNT_PENDING"),
    EMAIL_NOT_FOUND("EMAIL_NOT_FOUND"),
    EMAIL_ALREADY_VERIFIED("EMAIL_ALREADY_VERIFIED"),
    INVALID_VERIFICATION_CODE("INVALID_VERIFICATION_CODE"),
    SUCCESS("SUCCESS"),
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND");

    private final String value;

    ResponseMessage(String value) {
        this.value = value;
    }
}