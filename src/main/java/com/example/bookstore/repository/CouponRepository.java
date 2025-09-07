package com.example.bookstore.repository;

import com.example.bookstore.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    // Custom query methods will be added here later
}
