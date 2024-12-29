package com.marhasoft.stock_control_api.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}