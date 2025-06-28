package com.example.aruba.senddocumentsystem.internalnotificator.domain.exception;

public class SkipMessageException extends RuntimeException {
    public SkipMessageException(String message) {
        super(message);
    }
}
