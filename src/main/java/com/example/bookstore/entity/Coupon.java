package com.example.bookstore.entity;

import com.example.bookstore.enums.DiscountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer couponId;
    
    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, length = 20)
    private DiscountType discountType;
    
    @Column(name = "discount_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountValue;
    
    @Column(name = "min_order_value", precision = 12, scale = 2)
    private BigDecimal minOrderValue;
    
    @Column(name = "max_discount", precision = 12, scale = 2)
    private BigDecimal maxDiscount;
    
    @Column(name = "start_at")
    private LocalDateTime startAt;
    
    @Column(name = "end_at")
    private LocalDateTime endAt;
    
    @Column(name = "usage_limit_total")
    private Integer usageLimitTotal;
    
    @Column(name = "usage_limit_per_user")
    private Integer usageLimitPerUser;
    
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Business methods
    public boolean isCurrentlyValid() {
        if (!isActive) return false;
        
        LocalDateTime now = LocalDateTime.now();
        boolean afterStart = startAt == null || now.isAfter(startAt);
        boolean beforeEnd = endAt == null || now.isBefore(endAt);
        
        return afterStart && beforeEnd;
    }
    
    public boolean isValidForAmount(BigDecimal orderAmount) {
        return minOrderValue == null || orderAmount.compareTo(minOrderValue) >= 0;
    }
    
    public BigDecimal calculateDiscount(BigDecimal orderAmount) {
        if (!isValidForAmount(orderAmount)) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal discount;
        switch (discountType) {
            case PERCENT:
                discount = orderAmount.multiply(discountValue.divide(BigDecimal.valueOf(100)));
                break;
            case FIXED:
                discount = discountValue;
                break;
            case FREESHIP:
                discount = BigDecimal.ZERO; // Shipping fee will be handled separately
                break;
            default:
                discount = BigDecimal.ZERO;
        }
        
        // Apply max discount limit
        if (maxDiscount != null && discount.compareTo(maxDiscount) > 0) {
            discount = maxDiscount;
        }
        
        return discount;
    }
}