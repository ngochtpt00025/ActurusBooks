package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class CartItemDTO {
    private Integer cartItemId;
    private Integer cartId;
    private Integer editionId;
    private Integer quantity;
    private LocalDateTime addedAt;
    
    // Additional fields for UI
    private EditionDTO edition;
    private BigDecimal subTotal;
    private String displayTitle;
    private String priceDisplay;
    private String imageUrl;
    private Boolean inStock;
    
    // Helper methods
    public BigDecimal getSubTotal() {
        if (edition != null && quantity != null) {
            return edition.getCurrentPrice().multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }
    
    public String getDisplayTitle() {
        return edition != null ? edition.getDisplayTitle() : "Unknown Product";
    }
    
    public String getPriceDisplay() {
        return edition != null ? edition.getPriceDisplay() : "0 VND";
    }
    
    public String getImageUrl() {
        return edition != null ? edition.getImages().get(0).getDisplayUrl() : "/img/placeholder.png";
    }
    
    public Boolean getInStock() {
        return edition != null ? edition.getInStock() : false;
    }
}
