package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Series")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Integer seriesId;
    
    @Column(name = "title", nullable = false, length = 250)
    private String title;
    
    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;
    
    @Column(name = "cover_image", length = 1000)
    private String coverImage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Relationships
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}