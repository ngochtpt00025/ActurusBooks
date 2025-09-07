package com.example.bookstore.repository;

import com.example.bookstore.entity.EditionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionImageRepository extends JpaRepository<EditionImage, Integer> {
    // Custom query methods will be added here later
}
