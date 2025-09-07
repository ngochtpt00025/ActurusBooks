package com.example.bookstore.enums;

public enum OrderStatus {
    PENDING("pending"),
    CONFIRMED("confirmed"),
    PROCESSING("processing"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELLED("cancelled"),
    RETURNED("returned");
    
    private final String value;
    
    OrderStatus(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static OrderStatus fromValue(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown OrderStatus value: " + value);
    }
}