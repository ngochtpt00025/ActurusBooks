package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cart_Items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Integer cartItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", nullable = false)
    private Edition edition;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
    
    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
    }
    
    // Business methods
    public BigDecimal getSubTotal() {
        if (edition == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return edition.getCurrentPrice().multiply(BigDecimal.valueOf(quantity));
    }
}