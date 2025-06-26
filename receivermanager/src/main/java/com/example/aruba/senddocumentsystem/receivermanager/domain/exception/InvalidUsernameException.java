package com.example.aruba.senddocumentsystem.receivermanager.domain.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
