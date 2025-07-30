package com.hemanth.wallet.db.entity;

import java.time.LocalDateTime;

public class ErrorResponse {
    private Long timestamp;
    private String ErrorCode;
    private String ErrorDetails;

    public ErrorResponse(Long timestamp ,String ErrorCode ,String ErrorDetails){
        this.ErrorDetails = ErrorDetails;
        this.ErrorCode= ErrorCode;
        this.timestamp = timestamp;

    }
    public ErrorResponse(){
        this.timestamp = System.currentTimeMillis();
    }


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorDetails() {
        return ErrorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        ErrorDetails = errorDetails;
    }
}
