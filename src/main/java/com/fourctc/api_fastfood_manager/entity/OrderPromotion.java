package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "order_promotion") // Bảng order_promotion
public class OrderPromotion {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderid")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "promotionid", referencedColumnName = "promotionid")
    private Promotion promotion;
}