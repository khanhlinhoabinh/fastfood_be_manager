package com.fourctc.api_fastfood_manager.entity;
import java.io.Serializable;
import java.util.Objects;
public class MenuItemPromotionId implements Serializable {
    private Integer menuItemID;
    private Integer promotionID;

    // Default constructor
    public MenuItemPromotionId() {}

    // Constructor đầy đủ
    public MenuItemPromotionId(Integer menuItemID, Integer promotionID) {
        this.menuItemID = menuItemID;
        this.promotionID = promotionID;
    }

    // Getters & Setters
    public Integer getMenuItemID() { return menuItemID; }
    public void setMenuItemID(Integer menuItemID) { this.menuItemID = menuItemID; }

    public Integer getPromotionID() { return promotionID; }
    public void setPromotionID(Integer promotionID) { this.promotionID = promotionID; }

    // Bắt buộc phải override equals() và hashCode() để JPA so sánh khóa đúng
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemPromotionId)) return false;
        MenuItemPromotionId that = (MenuItemPromotionId) o;
        return Objects.equals(menuItemID, that.menuItemID) &&
                Objects.equals(promotionID, that.promotionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemID, promotionID);
    }
}


