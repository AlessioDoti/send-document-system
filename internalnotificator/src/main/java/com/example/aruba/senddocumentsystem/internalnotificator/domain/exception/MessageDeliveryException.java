package com.example.aruba.senddocumentsystem.internalnotificator.domain.exception;

public class MessageDeliveryException extends RuntimeException {
    public MessageDeliveryException(String message) {
        super(message);
    }
}
