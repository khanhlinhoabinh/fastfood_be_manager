package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    // 🔍 Tìm chi tiết đơn hàng theo mã đơn
    List<OrderDetail> findByOrder_OrderID(Integer orderID);

    // 🔍 Tìm chi tiết đơn hàng theo mã món
    List<OrderDetail> findByMenuItem_MenuItemID(Integer menuItemID);
}