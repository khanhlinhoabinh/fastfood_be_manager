package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MenuItemPromotion") // ✅ khớp với tên bảng trong MenuItem
public class MenuItemPromotion {

    @EmbeddedId
    private MenuItemPromotionId id;

    @ManyToOne
    @JoinColumn(name = "MenuItemID", insertable = false, updatable = false)
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "PromotionID", insertable = false, updatable = false)
    private Promotion promotion;

    public MenuItemPromotionId getId() {
        return id;
    }

    public void setId(MenuItemPromotionId id) {
        this.id = id;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}