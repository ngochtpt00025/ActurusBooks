package com.example.bookstore.repository;

import com.example.bookstore.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    // Custom query methods will be added here later
}
