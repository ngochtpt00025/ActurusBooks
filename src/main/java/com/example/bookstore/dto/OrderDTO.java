package com.example.bookstore.dto;

import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.enums.OrderType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class OrderDTO {
    private Integer orderId;
    private String orderCode;
    private Integer userId;
    private String userDisplayName;
    private OrderType orderType;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private BigDecimal subtotal;
    private BigDecimal discountTotal;
    private BigDecimal shippingFee;
    private BigDecimal taxTotal;
    private BigDecimal totalAmount;
    private Boolean paymentStatus;
    private LocalDateTime placedAt;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    
    // Additional fields for UI
    private List<OrderItemDTO> orderItems;
    private List<ShippingAddressDTO> shippingAddresses;
    private List<OrderStatusHistoryDTO> statusHistory;
    private List<PaymentDTO> payments;
    private String statusDisplay;
    private String paymentStatusDisplay;
    private String orderTypeDisplay;
    private String totalAmountDisplay;
    private Integer totalItems;
    
    // Helper methods
    public String getStatusDisplay() {
        return status != null ? status.getValue() : "Unknown";
    }
    
    public String getPaymentStatusDisplay() {
        return Boolean.TRUE.equals(paymentStatus) ? "Đã thanh toán" : "Chưa thanh toán";
    }
    
    public String getOrderTypeDisplay() {
        return orderType != null ? orderType.getValue() : "Unknown";
    }
    
    public String getTotalAmountDisplay() {
        return totalAmount != null ? String.format("%,.0f VND", totalAmount.doubleValue()) : "0 VND";
    }
    
    public Integer getTotalItems() {
        if (orderItems == null || orderItems.isEmpty()) {
            return 0;
        }
        return orderItems.stream()
                .mapToInt(OrderItemDTO::getQuantity)
                .sum();
    }
}
