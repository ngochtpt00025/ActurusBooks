package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class OrderItemDTO {
    private Integer orderItemId;
    private Integer orderId;
    private Integer editionId;
    private BigDecimal priceAtPurchase;
    private Integer quantity;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private EditionDTO edition;
    private BigDecimal subTotal;
    private String displayTitle;
    private String priceDisplay;
    private String imageUrl;
    
    // Helper methods
    public BigDecimal getSubTotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
    
    public String getDisplayTitle() {
        return edition != null ? edition.getDisplayTitle() : "Unknown Product";
    }
    
    public String getPriceDisplay() {
        return String.format("%,.0f VND", priceAtPurchase.doubleValue());
    }
    
    public String getImageUrl() {
        return edition != null ? edition.getImages().get(0).getDisplayUrl() : "/img/placeholder.png";
    }
}
