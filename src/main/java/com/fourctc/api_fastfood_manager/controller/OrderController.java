package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React FE gọi API
public class OrderController {

    @Autowired
    private OrderService orderService;

    // OrderController.java
    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;  // Trả về các đơn hàng bao gồm các thông tin chi tiết
    }
}