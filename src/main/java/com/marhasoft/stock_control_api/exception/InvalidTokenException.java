package com.marhasoft.stock_control_api.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super();
    }
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
