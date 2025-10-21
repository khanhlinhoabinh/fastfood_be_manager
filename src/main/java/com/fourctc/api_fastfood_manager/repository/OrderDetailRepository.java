package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findByOrder_OrderID(Integer orderID);
    List<OrderDetail> findByMenuItem_MenuItemID(Integer menuItemID);
    List<OrderDetail> findByOrder_OrderIDAndMenuItem_MenuItemID(Integer orderID, Integer menuItemID);
}