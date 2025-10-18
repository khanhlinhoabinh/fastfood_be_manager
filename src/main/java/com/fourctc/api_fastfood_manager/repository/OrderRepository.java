package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Lấy danh sách đơn hàng với các cột cần thiết
    List<Order> findAll();

    // Phân trang
    Page<Order> findAll(Pageable pageable); // Tự động hỗ trợ phân trang
    List<Order> findByCustomer_CustomerID(Integer customerID);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByStatus(String status);

    // Tìm kiếm kết hợp
    List<Order> findByCustomer_CustomerIDAndOrderDateBetweenAndStatus(
            Integer customerID,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String status
    );
}
