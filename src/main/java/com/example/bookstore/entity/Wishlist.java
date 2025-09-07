package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Wishlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer wishlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Relationships
    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WishlistItem> wishlistItems;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (name == null || name.trim().isEmpty()) {
            name = "My Wishlist";
        }
    }
}