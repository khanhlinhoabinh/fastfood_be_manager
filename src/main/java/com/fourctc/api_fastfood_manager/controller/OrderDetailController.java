package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    // 🟢 API thêm chi tiết đơn hàng mới
    @PostMapping
    public OrderDetailDTO addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        return orderDetailService.addOrderDetail(orderDetailDTO);
    }

    // 🟡 API cập nhật chi tiết đơn hàng
    @PutMapping("/{id}")
    public OrderDetailDTO updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetailDTO dto) {
        return orderDetailService.updateOrderDetail(id, dto);
    // Sap xep
    @GetMapping("/sort")
    public List<OrderDetailDTO> getAllOrderDetails(
            @RequestParam(defaultValue = "quantity") String sortBy, // Mặc định sắp xếp theo mã món
            @RequestParam(defaultValue = "asc") String direction) {  // Mặc định sắp xếp tăng dần
        return orderDetailService.getAllOrderDetails(sortBy, direction);
    }
    // Lấy danh sách chi tiết đơn hàng với phân trang
    @GetMapping("/paged")
    public Page<OrderDetailDTO> getAllOrderDetails(
            @RequestParam(defaultValue = "1") int page, // Trang bắt đầu từ 1
            @RequestParam(defaultValue = "10") int size // Mặc định là 10 bản ghi mỗi trang
    ) {
        return orderDetailService.getOrderDetails(page, size);
    }
}
