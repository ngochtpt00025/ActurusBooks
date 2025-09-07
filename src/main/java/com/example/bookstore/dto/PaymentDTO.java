package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class PaymentDTO {
    private Integer paymentId;
    private Integer orderId;
    private String provider;
    private String providerTxnId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDateTime paidAt;
    private String rawPayload;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String providerDisplay;
    private String amountDisplay;
    private String statusDisplay;
    private String paidAtDisplay;
    private String currencyDisplay;
    
    // Helper methods
    public String getProviderDisplay() {
        return provider != null ? provider : "Unknown";
    }
    
    public String getAmountDisplay() {
        return amount != null ? String.format("%,.0f %s", amount.doubleValue(), currency) : "0 VND";
    }
    
    public String getStatusDisplay() {
        return status != null ? status : "Unknown";
    }
    
    public String getPaidAtDisplay() {
        return paidAt != null ? paidAt.toString() : "N/A";
    }
    
    public String getCurrencyDisplay() {
        return currency != null ? currency : "VND";
    }
}
