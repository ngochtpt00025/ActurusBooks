package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class PromotionDTO {
    private Integer promotionId;
    private String name;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Boolean isActive;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayName;
    private String shortDescription;
    private String discountValueDisplay;
    private String periodDisplay;
    private String statusDisplay;
    private Boolean isCurrentlyActive;
    private Integer totalEditions;
    private List<EditionDTO> editions;
    
    // Helper methods
    public String getDisplayName() {
        return name != null ? name : "Unknown Promotion";
    }
    
    public String getShortDescription() {
        if (description == null || description.length() <= 100) {
            return description;
        }
        return description.substring(0, 100) + "...";
    }
    
    public String getDiscountValueDisplay() {
        return discountValue != null ? String.format("%,.0f VND", discountValue.doubleValue()) : "0 VND";
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
    
    public Boolean getIsCurrentlyActive() {
        if (!Boolean.TRUE.equals(isActive)) return false;
        
        LocalDateTime now = LocalDateTime.now();
        boolean afterStart = startAt == null || now.isAfter(startAt);
        boolean beforeEnd = endAt == null || now.isBefore(endAt);
        
        return afterStart && beforeEnd;
    }
}
