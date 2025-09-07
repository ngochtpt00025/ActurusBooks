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
public class BookDTO {
    private Integer bookId;
    private Integer seriesId;
    private String seriesTitle;
    private String title;
    private BigDecimal volumeNumber;
    private String description;
    private String coverImage;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayTitle;
    private String shortDescription;
    private String volumeDisplay;
    private Integer totalEditions;
    private Double averageRating;
    private Integer totalReviews;
    private List<AuthorDTO> authors;
    private List<CategoryDTO> categories;
    private List<EditionDTO> editions;
    
    // Helper methods
    public String getDisplayTitle() {
        if (seriesTitle != null && volumeNumber != null) {
            return seriesTitle + " - Tập " + volumeNumber;
        }
        return title != null ? title : "Unknown Book";
    }
    
    public String getShortDescription() {
        if (description == null || description.length() <= 200) {
            return description;
        }
        return description.substring(0, 200) + "...";
    }
    
    public String getVolumeDisplay() {
        if (volumeNumber != null) {
            return "Tập " + volumeNumber;
        }
        return "";
    }
}
