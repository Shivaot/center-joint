package com.hitachi.epdi2.exception;

public class UserDefinedException extends RuntimeException {

    String message;

    public UserDefinedException() {

    }

    public UserDefinedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
