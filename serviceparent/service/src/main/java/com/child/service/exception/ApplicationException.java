package com.child.service.exception;

public class ApplicationException extends RuntimeException{

     ErrorStatus errorStatus;
     String message;

    public ApplicationException(ErrorStatus errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }


}
