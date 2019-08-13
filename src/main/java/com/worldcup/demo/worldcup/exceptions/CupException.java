package com.worldcup.demo.worldcup.exceptions;

public class CupException extends RuntimeException {

    public CupException(String message) {
        super(message);
    }

    public CupException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
