package com.example.bookstore.repository;

import com.example.bookstore.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
    // Custom query methods will be added here later
}
