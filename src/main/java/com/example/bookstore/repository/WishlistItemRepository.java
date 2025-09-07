package com.example.bookstore.repository;

import com.example.bookstore.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Integer> {
    // Custom query methods will be added here later
}
