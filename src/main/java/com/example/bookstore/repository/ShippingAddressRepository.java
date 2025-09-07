package com.example.bookstore.repository;

import com.example.bookstore.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    // Custom query methods will be added here later
}
