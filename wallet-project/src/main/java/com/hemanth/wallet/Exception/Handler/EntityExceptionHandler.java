package com.hemanth.wallet.Exception.Handler;

import com.hemanth.wallet.Exception.EntityNotFound;
import com.hemanth.wallet.Exception.WalletException;
import com.hemanth.wallet.db.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler( WalletException.class)
    public ResponseEntity<ErrorResponse> UserException (WalletException ex)
    {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());
        error.setErrorDetails(ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(error);
    }

}
