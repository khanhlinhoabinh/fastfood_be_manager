package com.fourctc.api_fastfood_manager.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "order_detail") // Bảng order_detail
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailid") // Cột orderdetailid
    private Integer orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false) // Cột orderid
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menuitemid", nullable = false) // Cột menuitemid
    private MenuItem menuItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unitprice")
    private Double unitPrice;

    @Column(name = "note")
    private String note;

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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
