package com.example.bookstore.repository;

import com.example.bookstore.entity.BookAuthor;
import com.example.bookstore.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
    // Custom query methods will be added here later
}
