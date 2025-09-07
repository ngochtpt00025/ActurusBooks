package com.example.bookstore.enums;

public enum DiscountType {
    PERCENT("percent"),
    FIXED("fixed"),
    FREESHIP("freeship");
    
    private final String value;
    
    DiscountType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static DiscountType fromValue(String value) {
        for (DiscountType type : DiscountType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown DiscountType value: " + value);
    }
}