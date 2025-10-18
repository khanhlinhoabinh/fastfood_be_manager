package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React FE gọi API
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Chức năng lấy danh sách
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders(); // Trả về danh sách DTO thay vì entity
    }

    // API phân trang đơn hàng (chỉ hiển thị thông tin đơn hàng mà không bao gồm thông tin khách hàng và nhân viên)
    @GetMapping("/paged")
    public Page<OrderDTO> getOrders(@RequestParam("page") int page, @RequestParam("size") int size) {
        return orderService.getOrders(page, size);
    }

    @GetMapping("/sort")
    public List<OrderDTO> getAllOrders(@RequestParam(defaultValue = "orderDate") String sortBy,
                                       @RequestParam(defaultValue = "asc") String direction) {
        return orderService.getAllOrders(sortBy, direction);
    }

}