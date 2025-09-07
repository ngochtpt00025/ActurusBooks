package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class SeriesDTO {
    private Integer seriesId;
    private String title;
    private String description;
    private String coverImage;
    private Integer authorId;
    private String authorName;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayTitle;
    private String shortDescription;
    private Integer totalBooks;
    private Integer totalEditions;
    private Double averageRating;
    private List<BookDTO> books;
    private List<EditionDTO> editions;
    
    // Helper methods
    public String getDisplayTitle() {
        return title != null ? title : "Unknown Series";
    }
    
    public String getShortDescription() {
        if (description == null || description.length() <= 150) {
            return description;
        }
        return description.substring(0, 150) + "...";
    }
}
