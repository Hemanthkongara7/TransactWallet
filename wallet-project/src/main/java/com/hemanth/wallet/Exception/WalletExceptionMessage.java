package com.hemanth.wallet.Exception;

import org.springframework.http.HttpStatus;

public enum WalletExceptionMessage
{
    UN_AUTHORIZED("014001", "Not Authorised to Access", HttpStatus.UNAUTHORIZED),
    INVALID_LOGIN("014002","Invalid Login Credentials",HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("014003","User Does Not Exist",HttpStatus.NOT_FOUND),
    INVALID_USER_STATUS("014004","User is Inactive",HttpStatus.NOT_FOUND),
    IN_SUFFICIENT_BALANCE("014005","Insufficient balance",HttpStatus.BAD_REQUEST),
    INVALID_TRANSACTION_ID("014006","Invalid transaction Id",HttpStatus.BAD_REQUEST),
    REQUEST_ALREADY_ACCESSED("014008","Request cannot be updated",HttpStatus.BAD_REQUEST),
    SELF_TRANSACTION_NOT_ALLOWED("014007","Self transaction not allowed",HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    WalletExceptionMessage(String errorCode, String errorMessage ,  HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage= errorMessage;
        this.httpStatus = httpStatus;
    }
    public String errorCode() {
        return errorCode;
    }

    public String errorMessage() {
        return errorMessage;
    }
    public HttpStatus httpStatus() {
        return httpStatus;
    }


}
