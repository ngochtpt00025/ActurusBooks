package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class WishlistItemDTO {
    private Integer wishlistItemId;
    private Integer wishlistId;
    private Integer editionId;
    private LocalDateTime addedAt;
    
    // Additional fields for UI
    private EditionDTO edition;
    private String displayTitle;
    private String priceDisplay;
    private String imageUrl;
    private Boolean inStock;
    private String timeDisplay;
    
    // Helper methods
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
    
    public String getTimeDisplay() {
        return addedAt != null ? addedAt.toString() : "";
    }
}
