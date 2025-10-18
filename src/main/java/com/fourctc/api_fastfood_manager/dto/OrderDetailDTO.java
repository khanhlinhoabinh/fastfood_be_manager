package com.fourctc.api_fastfood_manager.dto;

public class OrderDetailDTO {
    private Integer orderDetailID;
    private Integer orderID;
    private Integer menuItemID;
    private Integer quantity;
    private Double unitPrice;
    private String note;

    // Constructor
    public OrderDetailDTO(Integer orderDetailID, Integer orderID, Integer menuItemID, Integer quantity, Double unitPrice, String note) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.menuItemID = menuItemID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    // Getters and Setters
    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(Integer menuItemID) {
        this.menuItemID = menuItemID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
