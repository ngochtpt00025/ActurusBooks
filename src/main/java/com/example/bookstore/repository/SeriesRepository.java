package com.example.bookstore.repository;

import com.example.bookstore.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    // Custom query methods will be added here later
}
