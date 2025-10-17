package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // OrderService.java
    public List<Order> getAllOrders() {
        return orderRepository.findAll();  // Lấy tất cả đơn hàng từ DB, sẽ tự động bao gồm cả orderDetails nếu đã được ánh xạ đúng
    }

    @Transactional
    public void deleteOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getPromotions().clear(); // Xoá liên kết với promotions
            orderRepository.delete(order); // Xoá order và các liên kết cascade
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + id);
        }
    }

}
