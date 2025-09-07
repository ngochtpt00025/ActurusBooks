package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Order_Status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private Integer orderStatusId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    
    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;
    
    @Column(name = "previous_status", length = 50)
    private String previousStatus;
    
    @Column(name = "updated_by", length = 100)
    private String updatedBy;
    
    @Column(name = "note", length = 500)
    private String note;
    
    @Column(name = "is_automatic", nullable = false)
    private Boolean isAutomatic;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}