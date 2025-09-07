package com.example.bookstore.repository;

import com.example.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    // Custom query methods will be added here later
}
