package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React FE gọi API
public class OrderController {

    @Autowired
    private OrderService orderService;

    // OrderController.java
    @GetMapping

    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders(); // Trả về danh sách DTO thay vì entity
    }
}