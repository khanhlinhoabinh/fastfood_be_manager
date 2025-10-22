package com.fourctc.api_fastfood_manager.dto;

import com.fourctc.api_fastfood_manager.entity.Promotion;

import java.time.LocalDate;

public class PromotionDTO {
    private Integer promotionID;
    private String name;
    private String type;
    private Double discountPercent;
    private LocalDate expiryDate;

    // Constructor chuyển từ entity sang DTO
    public PromotionDTO(Promotion promotion) {
        this.promotionID = promotion.getPromotionID();
        this.name = promotion.getName();
        this.type = promotion.getType();
        this.discountPercent = promotion.getDiscountPercent();
        this.expiryDate = promotion.getExpiryDate();
    }

    // Getters và Setters
    public Integer getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Integer promotionID) {
        this.promotionID = promotionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}