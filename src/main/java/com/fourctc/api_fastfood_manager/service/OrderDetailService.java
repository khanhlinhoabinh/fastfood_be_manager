package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.entity.MenuItem;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import com.fourctc.api_fastfood_manager.repository.OrderDetailRepository;
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

    // 🟢 Thêm chi tiết đơn hàng mới
    public OrderDetailDTO addOrderDetail(OrderDetailDTO dto) {
        OrderDetail orderDetail = new OrderDetail();

        // Gán các thuộc tính
        Order order = new Order();
        order.setOrderID(dto.getOrderID()); // liên kết Order có sẵn
        orderDetail.setOrder(order);

        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemID(dto.getMenuItemID()); // liên kết món ăn có sẵn
        orderDetail.setMenuItem(menuItem);

        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setUnitPrice(dto.getUnitPrice());
        orderDetail.setNote(dto.getNote());

        // Lưu vào DB
        OrderDetail saved = orderDetailRepository.save(orderDetail);

        // Trả lại DTO
        return new OrderDetailDTO(
                saved.getOrderDetailID(),
                saved.getOrder().getOrderID(),
                saved.getMenuItem().getMenuItemID(),
                saved.getQuantity(),
                saved.getUnitPrice(),
                saved.getNote()
        );
    }

    // 🟡 Cập nhật chi tiết đơn hàng
    public OrderDetailDTO updateOrderDetail(Integer id, OrderDetailDTO dto) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id " + id));

        // Cập nhật thông tin
        if (dto.getOrderID() != null) {
            Order order = new Order();
            order.setOrderID(dto.getOrderID());
            existing.setOrder(order);
        }

        if (dto.getMenuItemID() != null) {
            MenuItem menuItem = new MenuItem();
            menuItem.setMenuItemID(dto.getMenuItemID());
            existing.setMenuItem(menuItem);
        }

        existing.setQuantity(dto.getQuantity());
        existing.setUnitPrice(dto.getUnitPrice());
        existing.setNote(dto.getNote());

        OrderDetail updated = orderDetailRepository.save(existing);

        return new OrderDetailDTO(
                updated.getOrderDetailID(),
                updated.getOrder().getOrderID(),
                updated.getMenuItem().getMenuItemID(),
                updated.getQuantity(),
                updated.getUnitPrice(),
                updated.getNote()
        );
    }
}
