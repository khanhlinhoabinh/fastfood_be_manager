package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import com.fourctc.api_fastfood_manager.repository.OrderDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Phương thức để lấy tất cả chi tiết đơn hàng và chuyển đổi thành DTO
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream().map(orderDetail -> new OrderDetailDTO(
                orderDetail.getOrderDetailID(),
                orderDetail.getOrder().getOrderID(), // Mã đơn
                orderDetail.getMenuItem().getMenuItemID(), // Mã món
                orderDetail.getQuantity(),
                orderDetail.getUnitPrice(),
                orderDetail.getNote()
        )).collect(Collectors.toList());
    }
    public void deleteOrderDetailById(Integer id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new EntityNotFoundException("OrderDetail not found with ID: " + id);
        }
        orderDetailRepository.deleteById(id);
    }

    public List<OrderDetail> searchByOrderID(Integer orderID) {
        return orderDetailRepository.findByOrder_OrderID(orderID);
    }

    public List<OrderDetail> searchByMenuItemID(Integer menuItemID) {
        return orderDetailRepository.findByMenuItem_MenuItemID(menuItemID);
    }

    public List<OrderDetail> searchByOrderIDAndMenuItemID(Integer orderID, Integer menuItemID) {
        return orderDetailRepository.findByOrder_OrderIDAndMenuItem_MenuItemID(orderID, menuItemID);
    }
}
