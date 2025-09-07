package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class WishlistDTO {
    private Integer wishlistId;
    private Integer userId;
    private String userDisplayName;
    private String name;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private List<WishlistItemDTO> wishlistItems;
    private String displayName;
    private Integer totalItems;
    private Boolean isEmpty;
    private String timeDisplay;
    
    // Helper methods
    public String getDisplayName() {
        return name != null ? name : "My Wishlist";
    }
    
    public Integer getTotalItems() {
        return wishlistItems != null ? wishlistItems.size() : 0;
    }
    
    public Boolean getIsEmpty() {
        return wishlistItems == null || wishlistItems.isEmpty();
    }
    
    public String getTimeDisplay() {
        return createdAt != null ? createdAt.toString() : "";
    }
}
