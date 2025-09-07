package com.example.bookstore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class ShippingAddressDTO {
    private Integer addressId;
    private Integer orderId;
    private Integer userId;
    private String recipientName;
    private String phone;
    private String wardId;
    private String districtId;
    private String addressLine;
    
    // Additional fields for UI
    private String fullAddress;
    private String wardName;
    private String districtName;
    private String provinceName;
    private String displayAddress;
    
    // Helper methods
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (addressLine != null) {
            sb.append(addressLine);
        }
        if (wardName != null) {
            sb.append(", ").append(wardName);
        }
        if (districtName != null) {
            sb.append(", ").append(districtName);
        }
        if (provinceName != null) {
            sb.append(", ").append(provinceName);
        }
        return sb.toString();
    }
    
    public String getDisplayAddress() {
        return getFullAddress();
    }
}
