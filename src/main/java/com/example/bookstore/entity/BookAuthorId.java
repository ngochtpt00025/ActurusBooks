package com.example.bookstore.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorId implements Serializable {
    private Integer book;
    private Integer author;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthorId that = (BookAuthorId) o;
        return Objects.equals(book, that.book) && Objects.equals(author, that.author);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}