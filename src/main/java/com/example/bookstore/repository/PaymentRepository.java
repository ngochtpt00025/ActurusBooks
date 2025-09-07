package com.example.bookstore.repository;

import com.example.bookstore.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Custom query methods will be added here later
}
