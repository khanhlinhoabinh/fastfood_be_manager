package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name = "menu_item") // Bảng menu_item
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuitemid") // Cột menuitemid
    private Integer menuItemID;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "stockquantity")
    private Integer stockQuantity;

    @Column(name = "preptime")
    private Integer prepTime;

    // Liên kết ManyToMany với Promotion
    @ManyToMany
    @JoinTable(
            name = "MenuItemPromotion",
            joinColumns = @JoinColumn(name = "MenuItemID"),
            inverseJoinColumns = @JoinColumn(name = "PromotionID")
    )
    private Set<Promotion> promotions;

    // Getter & Setter
    public Integer getMenuItemID() { return menuItemID; }
    public void setMenuItemID(Integer menuItemID) { this.menuItemID = menuItemID; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Integer getPrepTime() { return prepTime; }
    public void setPrepTime(Integer prepTime) { this.prepTime = prepTime; }

    public Set<Promotion> getPromotions() { return promotions; }
    public void setPromotions(Set<Promotion> promotions) { this.promotions = promotions; }
}
