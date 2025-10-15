package com.fourctc.api_fastfood_manager.entity;
import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "MenuItemPromotion")
@IdClass(MenuItemPromotionId.class)
public class MenuItemPromotion implements Serializable {

    @Id
    private Integer menuItemID;

    @Id
    private Integer promotionID;
}
