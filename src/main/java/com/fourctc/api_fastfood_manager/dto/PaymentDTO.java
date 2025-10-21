package com.fourctc.api_fastfood_manager.dto;

import java.time.LocalDateTime;

/**
 * DTO dùng cho create/update Payment để client chỉ cần gửi orderId (Integer),
 * tránh phải gửi object Order nested, giảm lỗi deserialize.
 */
public class PaymentDTO {
    private Integer orderId;         // ✅ ID của Order cần liên kết
    private String method;
    private Double amount;
    private Double changeAmount;
    private LocalDateTime paymentDate; // optional khi create

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}