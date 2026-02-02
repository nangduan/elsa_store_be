package com.example.elsa_store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    UN_AUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZED(1002, "You do not have permission", HttpStatus.FORBIDDEN),
    INTERNAL_ERROR(1003, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1004, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1005, "Username must be at least {min} character", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1006, "password must be al least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1007, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    INCORRECT_ACCOUNT_OR_PASSWORD(1008, "Incorrect account or password", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1009, "Resource not found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(1010, "Bad request", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
