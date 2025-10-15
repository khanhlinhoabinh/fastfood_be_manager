package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "OrderPromotion")
@IdClass(OrderPromotionId.class)
public class OrderPromotion implements Serializable {

    @Id
    private Integer orderID;

    @Id
    private Integer promotionID;
}