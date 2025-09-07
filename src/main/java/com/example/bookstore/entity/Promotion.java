package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;
    
    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;
    
    @Column(name = "discount_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountValue;
    
    @Column(name = "start_at")
    private LocalDateTime startAt;
    
    @Column(name = "end_at")
    private LocalDateTime endAt;
    
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Relationships
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromotionEdition> promotionEditions;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Business methods
    public boolean isCurrentlyActive() {
        if (!isActive) return false;
        
        LocalDateTime now = LocalDateTime.now();
        boolean afterStart = startAt == null || now.isAfter(startAt);
        boolean beforeEnd = endAt == null || now.isBefore(endAt);
        
        return afterStart && beforeEnd;
    }
}