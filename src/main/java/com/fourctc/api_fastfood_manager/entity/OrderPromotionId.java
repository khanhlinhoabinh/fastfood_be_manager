package com.fourctc.api_fastfood_manager.entity;

import java.io.Serializable;
import java.util.Objects;
public class OrderPromotionId implements Serializable {
    private Integer orderID;
    private Integer promotionID;

    // Default constructor
    public OrderPromotionId() {}

    // Constructor đầy đủ
    public OrderPromotionId(Integer orderID, Integer promotionID) {
        this.orderID = orderID;
        this.promotionID = promotionID;
    }

    // Getters & Setters
    public Integer getOrderID() { return orderID; }
    public void setOrderID(Integer orderID) { this.orderID = orderID; }

    public Integer getPromotionID() { return promotionID; }
    public void setPromotionID(Integer promotionID) { this.promotionID = promotionID; }

    // equals() & hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPromotionId)) return false;
        OrderPromotionId that = (OrderPromotionId) o;
        return Objects.equals(orderID, that.orderID) &&
                Objects.equals(promotionID, that.promotionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, promotionID);
    }
}


