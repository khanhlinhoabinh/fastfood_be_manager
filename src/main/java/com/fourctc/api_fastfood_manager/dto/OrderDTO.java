package com.fourctc.api_fastfood_manager.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Integer orderID;
    private Integer customerID;
    private Integer staffID;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String status;

    public OrderDTO() {}

    public OrderDTO(Integer orderID, Integer customerID, Integer staffID, LocalDateTime orderDate, Double totalAmount, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.staffID = staffID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}