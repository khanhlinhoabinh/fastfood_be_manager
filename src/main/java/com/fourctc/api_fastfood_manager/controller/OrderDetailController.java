package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import com.fourctc.api_fastfood_manager.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "*") // Cho phép React FE gọi API
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Lấy tất cả chi tiết đơn hàng
    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Integer id) {
        try {
            orderDetailService.deleteOrderDetailById(id);
            return ResponseEntity.ok("Order detail deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<OrderDetail> searchOrderDetails(
            @RequestParam(required = false) Integer orderID,
            @RequestParam(required = false) Integer menuItemID
    ) {
        if (orderID != null && menuItemID != null) {
            return orderDetailService.searchByOrderIDAndMenuItemID(orderID, menuItemID);
        } else if (orderID != null) {
            return orderDetailService.searchByOrderID(orderID);
        } else if (menuItemID != null) {
            return orderDetailService.searchByMenuItemID(menuItemID);
        } else {
            return List.of(); // Trả về danh sách rỗng nếu không có tham số
        }
    }
}
