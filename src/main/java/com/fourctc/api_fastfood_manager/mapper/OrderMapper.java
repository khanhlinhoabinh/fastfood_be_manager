package com.fourctc.api_fastfood_manager.mapper;

import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.entity.Order;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        return new OrderDTO(
                order.getOrderID(),
                order.getCustomer() != null ? order.getCustomer().getCustomerID() : null,
                order.getStaff() != null ? order.getStaff().getStaffID() : null,
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus()
        );
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;

        Order order = new Order();
        order.setOrderID(dto.getOrderID());
        order.setOrderDate(dto.getOrderDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());
        // Chưa set customer và staff vì cần fetch từ DB
        return order;
    }
}