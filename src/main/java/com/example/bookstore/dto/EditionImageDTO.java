package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class EditionImageDTO {
    private Integer imageId;
    private Integer editionId;
    private String imageUrl;
    private Boolean isCover;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayUrl;
    private String altText;
    private String thumbnailUrl;
    
    // Helper methods
    public String getDisplayUrl() {
        return imageUrl != null ? imageUrl : "/img/placeholder.png";
    }
    
    public String getAltText() {
        return "Hình ảnh sản phẩm " + editionId;
    }
    
    public String getThumbnailUrl() {
        // In real implementation, you might generate thumbnail URLs
        return getDisplayUrl();
    }
}
