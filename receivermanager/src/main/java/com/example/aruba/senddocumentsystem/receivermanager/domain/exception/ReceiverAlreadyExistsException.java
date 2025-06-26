package com.example.aruba.senddocumentsystem.receivermanager.domain.exception;

public class ReceiverAlreadyExistsException extends RuntimeException {
    public ReceiverAlreadyExistsException(String message) {
        super(message);
    }
}
