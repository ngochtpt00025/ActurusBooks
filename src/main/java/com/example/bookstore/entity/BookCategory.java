package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book_Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(BookCategoryId.class)
public class BookCategory {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}