package com.example.bookstore.enums;

public enum OrderType {
    ONLINE("online"),
    OFFLINE("offline");
    
    private final String value;
    
    OrderType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static OrderType fromValue(String value) {
        for (OrderType type : OrderType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown OrderType value: " + value);
    }
}