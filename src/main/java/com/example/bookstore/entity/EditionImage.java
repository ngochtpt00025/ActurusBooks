package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Edition_Images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", nullable = false)
    private Edition edition;
    
    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;
    
    @Column(name = "is_cover", nullable = false)
    @Builder.Default
    private Boolean isCover = false;
    
    @Column(name = "sort_order", nullable = false)
    @Builder.Default
    private Integer sortOrder = 0;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}