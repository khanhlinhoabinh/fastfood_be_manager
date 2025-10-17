package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // OrderService.java
    public List<Order> getAllOrders() {
        return orderRepository.findAll();  // Lấy tất cả đơn hàng từ DB, sẽ tự động bao gồm cả orderDetails nếu đã được ánh xạ đúng
    }
}
