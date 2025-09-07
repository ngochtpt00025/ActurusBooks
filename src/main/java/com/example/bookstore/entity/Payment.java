package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    
    @Column(name = "provider", nullable = false, length = 100)
    private String provider;
    
    @Column(name = "provider_txn_id", length = 200)
    private String providerTxnId;
    
    @Column(name = "amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "currency", nullable = false, length = 3)
    @Builder.Default
    private String currency = "VND";
    
    @Column(name = "status", nullable = false, length = 30)
    private String status;
    
    @Column(name = "paid_at")
    private LocalDateTime paidAt;
    
    @Column(name = "raw_payload", columnDefinition = "NVARCHAR(MAX)")
    private String rawPayload;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}