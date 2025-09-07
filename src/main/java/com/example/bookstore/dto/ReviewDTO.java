package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class ReviewDTO {
    private Integer reviewId;
    private Integer userId;
    private String userDisplayName;
    private Integer editionId;
    private String editionTitle;
    private Byte rating;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayTitle;
    private String shortContent;
    private String ratingDisplay;
    private String timeDisplay;
    private String userAvatar;
    private Boolean isVerified;
    
    // Helper methods
    public String getDisplayTitle() {
        return title != null ? title : "Đánh giá của " + userDisplayName;
    }
    
    public String getShortContent() {
        if (content == null || content.length() <= 200) {
            return content;
        }
        return content.substring(0, 200) + "...";
    }
    
    public String getRatingDisplay() {
        if (rating == null) return "0/5";
        
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString() + " (" + rating + "/5)";
    }
    
    public String getTimeDisplay() {
        return createdAt != null ? createdAt.toString() : "";
    }
    
    public String getUserAvatar() {
        // In real implementation, you might have user avatar URLs
        return "/img/default-avatar.png";
    }
    
    public Boolean getIsVerified() {
        // In real implementation, you might check if user is verified
        return true;
    }
}
