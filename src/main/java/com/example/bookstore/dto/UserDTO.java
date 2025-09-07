package com.example.bookstore.dto;

import com.example.bookstore.enums.UserRole;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class UserDTO {
    private Integer userId;
    private String fullName;
    private String email;
    private String phone;
    private Boolean gender;
    private LocalDate birthday;
    private LocalDate hireDate;
    private String citizenId;
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for UI
    private String displayName;
    private String roleDisplay;
    private String statusDisplay;
    private Integer totalOrders;
    private Integer totalReviews;
    private Integer totalWishlists;
    
    // Address information
    private List<UserAddressDTO> addresses;
    
    // Helper methods
    public String getDisplayName() {
        return fullName != null ? fullName : email;
    }
    
    public String getRoleDisplay() {
        return role != null ? role.getValue() : "Unknown";
    }
    
    public String getStatusDisplay() {
        return Boolean.TRUE.equals(isActive) ? "Active" : "Inactive";
    }
}
