package com.fourctc.api_fastfood_manager.dto;

public class StaffDTO {

    private Integer staffID;
    private String name;
    private String position;
    private String shift;
    private Double salary;

    public StaffDTO() {}

    public StaffDTO(Integer staffID, String name, String position, String shift, Double salary) {
        this.staffID = staffID;
        this.name = name;
        this.position = position;
        this.shift = shift;
        this.salary = salary;
    }

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
}