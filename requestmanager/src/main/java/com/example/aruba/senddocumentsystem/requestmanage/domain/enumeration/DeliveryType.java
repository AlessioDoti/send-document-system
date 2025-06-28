package com.example.aruba.senddocumentsystem.requestmanage.domain.enumeration;

public enum DeliveryType {
    STANDARD,
    CERTIFIED;

    public static DeliveryType fromValue(String value) {
        for (DeliveryType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown DocumentType: " + value);
    }
}
