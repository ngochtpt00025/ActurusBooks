package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class CartDTO {
    private Integer cartId;
    private Integer userId;
    private String anonymousToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for UI
    private List<CartItemDTO> cartItems;
    private BigDecimal totalAmount;
    private Integer totalItems;
    private Boolean isEmpty;
    private String userDisplayName;
    
    // Helper methods
    public BigDecimal getTotalAmount() {
        if (cartItems == null || cartItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return cartItems.stream()
                .map(CartItemDTO::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public Integer getTotalItems() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0;
        }
        return cartItems.stream()
                .mapToInt(CartItemDTO::getQuantity)
                .sum();
    }
    
    public Boolean getIsEmpty() {
        return cartItems == null || cartItems.isEmpty();
    }
}
