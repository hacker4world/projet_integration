package com.topone.projet_integration.DTO;

public class ApiResponseDto<ResponseDataType> {
    private int responseCode;
    private String responseMessage;
    private ResponseDataType responseData;

    public ApiResponseDto(int responseCode, String responseMessage, ResponseDataType responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public ApiResponseDto(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseDataType getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataType responseData) {
        this.responseData = responseData;
    }
}
