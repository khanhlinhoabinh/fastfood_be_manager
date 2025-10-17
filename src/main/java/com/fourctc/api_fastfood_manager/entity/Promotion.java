package com.fourctc.api_fastfood_manager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "promotion") // Bảng promotion
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionid") // Cột promotionid
    private Integer promotionID;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "discountpercent")
    private Double discountPercent;

    @Column(name = "expirydate")
    private LocalDate expiryDate;

    @ManyToMany(mappedBy = "promotions")
    private Set<MenuItem> menuItems;

    @ManyToMany(mappedBy = "promotions")
    private Set<Order> orders;

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

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
