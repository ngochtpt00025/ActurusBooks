package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class OrderStatusHistoryDTO {
    private Integer orderStatusId;
    private Integer orderId;
    private String statusName;
    private String previousStatus;
    private String updatedBy;
    private String note;
    private Boolean isAutomatic;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private String statusDisplay;
    private String previousStatusDisplay;
    private String updatedByDisplay;
    private String noteDisplay;
    private String timeDisplay;
    
    // Helper methods
    public String getStatusDisplay() {
        return statusName != null ? statusName : "Unknown";
    }
    
    public String getPreviousStatusDisplay() {
        return previousStatus != null ? previousStatus : "N/A";
    }
    
    public String getUpdatedByDisplay() {
        return updatedBy != null ? updatedBy : "System";
    }
    
    public String getNoteDisplay() {
        return note != null ? note : "";
    }
    
    public String getTimeDisplay() {
        return createdAt != null ? createdAt.toString() : "";
    }
}
