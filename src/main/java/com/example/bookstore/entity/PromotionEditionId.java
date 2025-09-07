package com.example.bookstore.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionEditionId implements Serializable {
    private Integer promotion;
    private Integer edition;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionEditionId that = (PromotionEditionId) o;
        return Objects.equals(promotion, that.promotion) && Objects.equals(edition, that.edition);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(promotion, edition);
    }
}