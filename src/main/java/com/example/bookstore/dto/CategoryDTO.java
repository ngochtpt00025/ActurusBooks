package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class CategoryDTO {
    private Integer categoryId;
    private String name;
    private Integer parentId;
    private String parentName;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayName;
    private String fullPath;
    private Integer level;
    private Integer totalBooks;
    private List<CategoryDTO> children;
    private List<BookDTO> books;
    
    // Helper methods
    public String getDisplayName() {
        return name != null ? name : "Unknown Category";
    }
    
    public String getFullPath() {
        if (parentName != null) {
            return parentName + " > " + name;
        }
        return name;
    }
}
