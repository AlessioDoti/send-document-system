package com.example.aruba.senddocumentsystem.receivermanager.domain.exception;

public class MessageDeliveryException extends RuntimeException {
    public MessageDeliveryException(String message) {
        super(message);
    }
}
