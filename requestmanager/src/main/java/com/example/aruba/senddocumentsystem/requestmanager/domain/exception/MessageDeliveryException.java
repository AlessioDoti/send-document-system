package com.example.aruba.senddocumentsystem.requestmanager.domain.exception;

public class MessageDeliveryException extends RuntimeException {
    public MessageDeliveryException(String message) {
        super(message);
    }
}
