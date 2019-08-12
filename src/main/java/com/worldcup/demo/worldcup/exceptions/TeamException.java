package com.worldcup.demo.worldcup.exceptions;

public class TeamException extends RuntimeException {

    public TeamException(String message) {
        super(message);
    }

    public TeamException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
