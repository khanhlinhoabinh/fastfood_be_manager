package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.dto.OrderDetailDTO;
import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.entity.OrderDetail;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // ✅ Lấy tất cả đơn hàng (không phân trang)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ Thêm mới đơn hàng
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);

        // Gán customer (nếu có)
        if (orderDTO.getCustomerID() != null) {
            customerRepository.findById(orderDTO.getCustomerID())
                    .ifPresent(order::setCustomer);
        }

        Order saved = orderRepository.save(order);
        return OrderMapper.toDTO(saved);
    }

    // ✅ Cập nhật đơn hàng
    public OrderDTO updateOrder(Integer id, OrderDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng ID: " + id));

        if (dto.getCustomerID() != null) {
            customerRepository.findById(dto.getCustomerID())
                    .ifPresent(order::setCustomer);
        }

        order.setOrderDate(dto.getOrderDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());

        Order updated = orderRepository.save(order);
        return OrderMapper.toDTO(updated);
    }

    // ✅ Xóa đơn hàng
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng ID: " + id));

        // Xóa quan hệ nếu có
        if (order.getPromotions() != null) {
            order.getPromotions().clear();
        }
        orderRepository.delete(order);
    }

    // ✅ Tìm kiếm chi tiết nâng cao
    public List<OrderDTO> searchOrders(Integer customerID, LocalDateTime startDate, LocalDateTime endDate, String status) {
        List<Order> orders = orderRepository.findByCustomer_CustomerIDAndOrderDateBetweenAndStatus(
                customerID, startDate, endDate, status
        );

        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    // ✅ Lấy danh sách có phân trang + tìm kiếm keyword
    public Page<OrderDTO> getOrdersPaged(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> ordersPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            ordersPage = orderRepository.searchOrders(keyword.trim(), pageable);
        } else {
            ordersPage = orderRepository.findAll(pageable);
        }

        return ordersPage.map(OrderMapper::toDTO);
    }

    // ✅ Lấy tất cả (sắp xếp theo trường)
    public List<OrderDTO> getAllOrdersSorted(String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        return orderRepository.findAll(sort)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }


}
