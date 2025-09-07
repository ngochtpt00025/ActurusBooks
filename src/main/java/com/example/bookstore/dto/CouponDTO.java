package com.example.bookstore.dto;

import com.example.bookstore.enums.DiscountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class CouponDTO {
    private Integer couponId;
    private String code;
    private String description;
    private DiscountType discountType;
    private BigDecimal discountValue;
    private BigDecimal minOrderValue;
    private BigDecimal maxDiscount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer usageLimitTotal;
    private Integer usageLimitPerUser;
    private Boolean isActive;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayCode;
    private String shortDescription;
    private String discountTypeDisplay;
    private String discountValueDisplay;
    private String minOrderValueDisplay;
    private String maxDiscountDisplay;
    private String periodDisplay;
    private String statusDisplay;
    private Boolean isCurrentlyValid;
    private String usageLimitDisplay;
    
    // Helper methods
    public String getDisplayCode() {
        return code != null ? code : "Unknown Code";
    }
    
    public String getShortDescription() {
        if (description == null || description.length() <= 100) {
            return description;
        }
        return description.substring(0, 100) + "...";
    }
    
    public String getDiscountTypeDisplay() {
        return discountType != null ? discountType.getValue() : "Unknown";
    }
    
    public String getDiscountValueDisplay() {
        if (discountValue == null) return "0";
        
        switch (discountType) {
            case PERCENT:
                return String.format("%.0f%%", discountValue.doubleValue());
            case FIXED:
                return String.format("%,.0f VND", discountValue.doubleValue());
            case FREESHIP:
                return "Miễn phí vận chuyển";
            default:
                return "0";
        }
    }
    
    public String getMinOrderValueDisplay() {
        return minOrderValue != null ? String.format("%,.0f VND", minOrderValue.doubleValue()) : "Không giới hạn";
    }
    
    public String getMaxDiscountDisplay() {
        return maxDiscount != null ? String.format("%,.0f VND", maxDiscount.doubleValue()) : "Không giới hạn";
    }
    
    public String getPeriodDisplay() {
        if (startAt != null && endAt != null) {
            return startAt.toString() + " - " + endAt.toString();
        } else if (startAt != null) {
            return "Từ " + startAt.toString();
        } else if (endAt != null) {
            return "Đến " + endAt.toString();
        }
        return "Không giới hạn";
    }
    
    public String getStatusDisplay() {
        return Boolean.TRUE.equals(isActive) ? "Active" : "Inactive";
    }
    
    public Boolean getIsCurrentlyValid() {
        if (!Boolean.TRUE.equals(isActive)) return false;
        
        LocalDateTime now = LocalDateTime.now();
        boolean afterStart = startAt == null || now.isAfter(startAt);
        boolean beforeEnd = endAt == null || now.isBefore(endAt);
        
        return afterStart && beforeEnd;
    }
    
    public String getUsageLimitDisplay() {
        if (usageLimitTotal != null && usageLimitPerUser != null) {
            return "Tổng: " + usageLimitTotal + ", Mỗi user: " + usageLimitPerUser;
        } else if (usageLimitTotal != null) {
            return "Tổng: " + usageLimitTotal;
        } else if (usageLimitPerUser != null) {
            return "Mỗi user: " + usageLimitPerUser;
        }
        return "Không giới hạn";
    }
}
