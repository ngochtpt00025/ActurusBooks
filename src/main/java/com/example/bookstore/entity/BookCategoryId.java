package com.example.bookstore.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryId implements Serializable {
    private Integer book;
    private Integer category;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategoryId that = (BookCategoryId) o;
        return Objects.equals(book, that.book) && Objects.equals(category, that.category);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(book, category);
    }
}