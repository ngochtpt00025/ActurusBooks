package com.example.bookstore.repository;

import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query methods will be added here later
    Optional<User> findByEmail(String email);
}
