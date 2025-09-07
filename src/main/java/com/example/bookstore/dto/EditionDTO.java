package com.example.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class EditionDTO {
    private Integer editionId;
    private Integer bookId;
    private String bookTitle;
    private Integer publisherId;
    private String publisherName;
    private String isbn;
    private Short publishYear;
    private String coverType;
    private String language;
    private BigDecimal listPrice;
    private BigDecimal salePrice;
    private Integer stockQuantity;
    private Integer pageCount;
    private String dimensions;
    private BigDecimal weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for UI
    private String displayTitle;
    private BigDecimal currentPrice;
    private Boolean inStock;
    private Boolean hasDiscount;
    private BigDecimal discountAmount;
    private Double discountPercentage;
    private String priceDisplay;
    private String stockStatus;
    private List<EditionImageDTO> images;
    private List<ReviewDTO> reviews;
    
    // Helper methods
    public String getDisplayTitle() {
        if (bookTitle != null && publishYear != null) {
            return bookTitle + " (" + publishYear + ")";
        }
        return bookTitle != null ? bookTitle : "Unknown Edition";
    }
    
    public BigDecimal getCurrentPrice() {
        return salePrice != null ? salePrice : listPrice;
    }
    
    public Boolean getInStock() {
        return stockQuantity != null && stockQuantity > 0;
    }
    
    public Boolean getHasDiscount() {
        return salePrice != null && listPrice != null && salePrice.compareTo(listPrice) < 0;
    }
    
    public BigDecimal getDiscountAmount() {
        if (getHasDiscount()) {
            return listPrice.subtract(salePrice);
        }
        return BigDecimal.ZERO;
    }
    
    public Double getDiscountPercentage() {
        if (getHasDiscount() && listPrice.compareTo(BigDecimal.ZERO) > 0) {
            return getDiscountAmount().divide(listPrice, 4, java.math.RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)).doubleValue();
        }
        return 0.0;
    }
    
    public String getPriceDisplay() {
        if (getHasDiscount()) {
            return String.format("%,.0f VND", getCurrentPrice().doubleValue()) + 
                   " (Giảm " + String.format("%.0f%%", getDiscountPercentage()) + ")";
        }
        return String.format("%,.0f VND", getCurrentPrice().doubleValue());
    }
    
    public String getStockStatus() {
        if (getInStock()) {
            return "Còn hàng (" + stockQuantity + " cuốn)";
        }
        return "Hết hàng";
    }
}
