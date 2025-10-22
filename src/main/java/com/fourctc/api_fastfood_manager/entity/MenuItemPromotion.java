package com.fourctc.api_fastfood_manager.entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item_promotion")
public class MenuItemPromotion {

    @EmbeddedId
    private MenuItemPromotionId id;

    // Getters and Setters
    public MenuItemPromotionId getId() {
        return id;
    }

    public void setId(MenuItemPromotionId id) {
        this.id = id;
    }
}
