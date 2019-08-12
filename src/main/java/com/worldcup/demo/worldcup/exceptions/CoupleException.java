package com.worldcup.demo.worldcup.exceptions;

public class CoupleException extends RuntimeException {

    public CoupleException(String message) {
        super(message);
    }

    public CoupleException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
