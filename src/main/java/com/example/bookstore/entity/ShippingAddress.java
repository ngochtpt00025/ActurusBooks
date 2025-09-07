package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Shipping_Addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "recipient_name", nullable = false, length = 150)
    private String recipientName;
    
    @Column(name = "phone", nullable = false, length = 30)
    private String phone;
    
    @Column(name = "ward_id", nullable = false, length = 10)
    private String wardId;
    
    @Column(name = "district_id", nullable = false, length = 10)
    private String districtId;
    
    @Column(name = "address_line", length = 500)
    private String addressLine;
    
    // Helper method
    @Transient
    public String getFullAddress() {
        return addressLine != null ? addressLine : "";
    }
}