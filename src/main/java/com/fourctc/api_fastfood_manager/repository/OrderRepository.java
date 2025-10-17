package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Lấy danh sách đơn hàng với các cột cần thiết
    List<Order> findAll();
}
