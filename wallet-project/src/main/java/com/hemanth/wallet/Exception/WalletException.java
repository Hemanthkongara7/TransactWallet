package com.hemanth.wallet.Exception;


import org.springframework.http.HttpStatus;

public class WalletException extends RuntimeException {

    private static final long serialVersionUID = -8012132456980746145L;

    private String errorCode;

    private String errorMessage;

    private HttpStatus httpStatus;

    public WalletException(String s) {
        super();
    }

    public WalletException(String message, String code , HttpStatus httpStatus) {
        super(message);
        this.errorMessage = message;
        this.errorCode = code;
        this.httpStatus = httpStatus;
    }

    public WalletException(WalletExceptionMessage exceptionEnum) {
        super(exceptionEnum.errorMessage());
        this.errorMessage = exceptionEnum.errorMessage();
        this.errorCode = exceptionEnum.errorCode();
        this.httpStatus = exceptionEnum.httpStatus();
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void setHttpStatus(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }




}
