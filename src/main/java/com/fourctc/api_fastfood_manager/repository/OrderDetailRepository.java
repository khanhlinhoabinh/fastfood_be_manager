package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Có thể thêm các phương thức truy vấn khác nếu cần
    // Tìm theo mã đơn
    List<OrderDetail> findByOrder_OrderID(Integer orderID);

    // Tìm theo mã món
    List<OrderDetail> findByMenuItem_MenuItemID(Integer menuItemID);

    // Tìm theo cả mã đơn và mã món
    List<OrderDetail> findByOrder_OrderIDAndMenuItem_MenuItemID(Integer orderID, Integer menuItemID);
}
