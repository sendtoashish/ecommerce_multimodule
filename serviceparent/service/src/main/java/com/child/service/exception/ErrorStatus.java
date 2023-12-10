package com.child.service.exception;

import org.springframework.http.HttpStatus;

public enum ErrorStatus {

DUPLICATE_RECORD("DUPLICATE_RECORD", HttpStatus.BAD_REQUEST);

    String status;
    HttpStatus httpStatus;

    ErrorStatus(String status, HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }
}
