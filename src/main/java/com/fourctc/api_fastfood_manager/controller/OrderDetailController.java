package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React FE gọi API
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Lấy tất cả chi tiết đơn hàng
    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
}
