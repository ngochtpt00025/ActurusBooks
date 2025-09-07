package com.example.bookstore.repository;

import com.example.bookstore.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    // Custom query methods will be added here later
}
