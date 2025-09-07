package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;
    
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "contact_info", length = 500)
    private String contactInfo;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Relationships
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Edition> editions;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}