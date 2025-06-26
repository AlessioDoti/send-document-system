package com.example.aruba.senddocumentsystem.receivermanager.domain.exception;

public class ReceiverNotFoundException extends RuntimeException {
    public ReceiverNotFoundException(String message) {
        super(message);
    }
}
