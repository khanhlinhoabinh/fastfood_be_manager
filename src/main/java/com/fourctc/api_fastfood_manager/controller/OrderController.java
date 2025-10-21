package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Cho phép React FE gọi API
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Lấy toàn bộ đơn hàng (không phân trang, không sắp xếp)
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // ✅ Thêm đơn hàng mới
    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO newOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // ✅ Cập nhật đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable Integer id,
            @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
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

    // ✅ Lấy danh sách có phân trang (KHÔNG sắp xếp)
    @GetMapping("/paged")
    public ResponseEntity<Page<OrderDTO>> getOrdersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword
    ) {
        Page<OrderDTO> ordersPage = orderService.getOrdersPaged(page, size, keyword);
        return ResponseEntity.ok(ordersPage);
    }

    // ✅ Lấy danh sách có sắp xếp (KHÔNG phân trang)
    @GetMapping("/sort")
    public ResponseEntity<List<OrderDTO>> getAllOrdersSorted(
            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        List<OrderDTO> sortedOrders = orderService.getAllOrdersSorted(sortBy, direction);
        return ResponseEntity.ok(sortedOrders);
    }

    // ✅ Tìm kiếm nâng cao theo customerID, khoảng thời gian, status
    @GetMapping("/search")
    public ResponseEntity<List<OrderDTO>> searchOrders(
            @RequestParam Integer customerID,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String status
    ) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<OrderDTO> results = orderService.searchOrders(customerID, start, end, status);
        return ResponseEntity.ok(results);
    }
}