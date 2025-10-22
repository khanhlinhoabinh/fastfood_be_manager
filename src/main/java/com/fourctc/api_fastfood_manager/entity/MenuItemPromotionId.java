package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MenuItemPromotionId implements Serializable {

    @Column(name = "MenuItemID") // ✅ khớp với ánh xạ trong MenuItem
    private Integer menuItemId;

    @Column(name = "PromotionID")
    private Integer promotionId;

    public MenuItemPromotionId() {}

    public MenuItemPromotionId(Integer menuItemId, Integer promotionId) {
        this.menuItemId = menuItemId;
        this.promotionId = promotionId;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemPromotionId)) return false;
        MenuItemPromotionId that = (MenuItemPromotionId) o;
        return Objects.equals(menuItemId, that.menuItemId) &&
                Objects.equals(promotionId, that.promotionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemId, promotionId);
    }
}
