package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Có thể thêm các phương thức truy vấn khác nếu cần
}
