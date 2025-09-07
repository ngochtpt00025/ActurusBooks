package com.example.bookstore.repository;

import com.example.bookstore.entity.PromotionEdition;
import com.example.bookstore.entity.PromotionEditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionEditionRepository extends JpaRepository<PromotionEdition, PromotionEditionId> {
    // Custom query methods will be added here later
}
