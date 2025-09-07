package com.example.bookstore.repository;

import com.example.bookstore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Custom query methods will be added here later
}
