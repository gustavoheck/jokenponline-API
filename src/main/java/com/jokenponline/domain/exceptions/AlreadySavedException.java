package com.jokenponline.domain.exceptions;

public class AlreadySavedException extends RuntimeException {
    public AlreadySavedException(String message) {
        super(message);
    }
}
