package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}