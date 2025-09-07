package com.example.bookstore.repository;

import com.example.bookstore.entity.BookCategory;
import com.example.bookstore.entity.BookCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, BookCategoryId> {
    // Custom query methods will be added here later
}
