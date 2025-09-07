package com.example.bookstore.repository;

import com.example.bookstore.entity.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {
    // Custom query methods will be added here later
}
