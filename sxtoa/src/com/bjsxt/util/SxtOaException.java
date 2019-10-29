package com.bjsxt.util;

public class SxtOaException extends RuntimeException {


    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public SxtOaException() {
    }

    public SxtOaException(String message) {
        super(message);
    }
}
