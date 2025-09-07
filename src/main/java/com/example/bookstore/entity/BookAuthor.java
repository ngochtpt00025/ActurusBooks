package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book_Authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(BookAuthorId.class)
public class BookAuthor {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    
    @Column(name = "role", length = 100)
    private String role; // Tác giả, Họa sĩ, etc.
}