package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Editions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edition_id")
    private Integer editionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    
    @Column(name = "isbn", length = 20, unique = true)
    private String isbn;
    
    @Column(name = "publish_year")
    private Short publishYear;
    
    @Column(name = "cover_type", length = 20)
    private String coverType;
    
    @Column(name = "language", length = 60)
    @Builder.Default
    private String language = "Vietnamese";
    
    @Column(name = "list_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal listPrice;
    
    @Column(name = "sale_price", precision = 12, scale = 2)
    private BigDecimal salePrice;
    
    @Column(name = "stock_quantity", nullable = false)
    @Builder.Default
    private Integer stockQuantity = 0;
    
    @Column(name = "page_count")
    private Integer pageCount;
    
    @Column(name = "dimensions", length = 100)
    private String dimensions;
    
    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EditionImage> images;
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WishlistItem> wishlistItems;
    
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromotionEdition> promotionEditions;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Business methods
    public BigDecimal getCurrentPrice() {
        return salePrice != null ? salePrice : listPrice;
    }
    
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    
    public boolean hasDiscount() {
        return salePrice != null && salePrice.compareTo(listPrice) < 0;
    }
    
    public BigDecimal getDiscountAmount() {
        if (hasDiscount()) {
            return listPrice.subtract(salePrice);
        }
        return BigDecimal.ZERO;
    }
    
    public double getDiscountPercentage() {
        if (hasDiscount()) {
            return getDiscountAmount().divide(listPrice, 4, java.math.RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)).doubleValue();
        }
        return 0.0;
    }
}