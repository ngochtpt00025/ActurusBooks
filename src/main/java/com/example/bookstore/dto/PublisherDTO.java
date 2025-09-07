package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class PublisherDTO {
    private Integer publisherId;
    private String name;
    private String address;
    private String contactInfo;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String displayName;
    private String shortAddress;
    private Integer totalEditions;
    private List<EditionDTO> editions;
    
    // Helper methods
    public String getDisplayName() {
        return name != null ? name : "Unknown Publisher";
    }
    
    public String getShortAddress() {
        if (address == null || address.length() <= 50) {
            return address;
        }
        return address.substring(0, 50) + "...";
    }
}
