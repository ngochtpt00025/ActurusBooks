package com.example.bookstore.entity;

import com.example.bookstore.enums.OrderStatus;
import com.example.bookstore.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    
    @Column(name = "order_code", nullable = false, unique = true, length = 50)
    private String orderCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 20)
    private OrderType orderType;
    
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;
    
    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;
    
    @Column(name = "discount_total", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal discountTotal = BigDecimal.ZERO;
    
    @Column(name = "shipping_fee", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal shippingFee = BigDecimal.ZERO;
    
    @Column(name = "tax_total", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal taxTotal = BigDecimal.ZERO;
    
    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;
    
    @Column(name = "payment_status", nullable = false)
    @Builder.Default
    private Boolean paymentStatus = false;
    
    @Column(name = "placed_at")
    private LocalDateTime placedAt;
    
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Legacy fields for backward compatibility
    private String phone;
    private String address;
    private String payment_method;
    private String proof_image;
    private String discount_code;
    private BigDecimal discount_amount;
    private Double discount_percentage;
    private String shipping_method;
    private String invoice_number;
    
    // Relationships
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShippingAddress> shippingAddresses;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderStatusHistory> statusHistory;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
    
    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        calculateTotalAmount();
        if (orderCode == null || orderCode.isEmpty()) {
            generateOrderCode();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        calculateTotalAmount();
    }
    
    // Business methods
    private void calculateTotalAmount() {
        if (subtotal != null) {
            totalAmount = subtotal.subtract(discountTotal).add(shippingFee).add(taxTotal);
        }
    }
    
    public void generateOrderCode() {
        this.orderCode = "ORD" + System.currentTimeMillis();
    }
    
    // Backward compatibility methods
    @Transient
    public Integer getOrder_id() {
        return orderId;
    }
    
    @Transient
    public void setOrder_id(Integer order_id) {
        this.orderId = order_id;
    }
    
    @Transient
    public LocalDateTime getOrder_date() {
        return orderDate;
    }
    
    @Transient
    public void setOrder_date(LocalDateTime order_date) {
        this.orderDate = order_date;
    }
    
    @Transient
    public BigDecimal getTotal_amount() {
        return totalAmount;
    }
    
    @Transient
    public void setTotal_amount(BigDecimal total_amount) {
        this.totalAmount = total_amount;
    }
    
    @Transient
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    @Transient
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    @Transient
    public String getStatus() {
        return status != null ? status.getValue() : null;
    }
    
    @Transient
    public void setStatus(String status) {
        this.status = OrderStatus.fromValue(status);
    }
    
    @Transient
    public String getPayment_status() {
        return paymentStatus ? "Đã thanh toán" : "Chưa thanh toán";
    }
    
    @Transient
    public void setPayment_status(String payment_status) {
        this.paymentStatus = "Đã thanh toán".equals(payment_status);
    }
}
