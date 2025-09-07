package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class AuthorDTO {
    private Integer authorId;
    private String name;
    private String biography;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayName;
    private String shortBiography;
    private Integer totalBooks;
    private Integer totalSeries;
    private List<BookDTO> books;
    private List<SeriesDTO> series;
    
    // Helper methods
    public String getDisplayName() {
        return name != null ? name : "Unknown Author";
    }
    
    public String getShortBiography() {
        if (biography == null || biography.length() <= 100) {
            return biography;
        }
        return biography.substring(0, 100) + "...";
    }
}
