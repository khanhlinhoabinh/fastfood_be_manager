package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Cho phép FE React gọi API
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Lấy toàn bộ đơn hàng
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // ✅ Thêm đơn hàng mới
    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderDTO));
    }

    // ✅ Cập nhật đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }

    // ✅ Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrderById(id);
            return ResponseEntity.ok("Xóa đơn hàng thành công.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đơn hàng ID: " + id);
        }
    }

    // ✅ Sắp xếp đơn hàng
    @GetMapping("/sort")
    public ResponseEntity<List<OrderDTO>> getAllOrdersSorted(
            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(orderService.getAllOrdersSorted(sortBy, direction));
    }

    // ✅ TÌM KIẾM ĐƠN GIẢN (CustomerID + Status)
    @GetMapping("/search")
    public ResponseEntity<List<OrderDTO>> searchOrders(
            @RequestParam(required = false) Integer customerID,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(orderService.searchOrdersSimple(customerID, status));
    }
    // ✅ Phân trang danh sách đơn hàng
    @GetMapping("/paged")
    public ResponseEntity<Page<OrderDTO>> getOrdersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Page<OrderDTO> orders = orderService.getOrdersPaged(page, size, sortBy, direction);
        return ResponseEntity.ok(orders);
    }


}
