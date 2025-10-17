package com.fourctc.api_fastfood_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "staff") // Bảng staff
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid") // Cột staffid
    private Integer staffID;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "shift")
    private String shift;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore // ✅ tránh lặp khi serialize JSON
    private List<Order> orders;

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
