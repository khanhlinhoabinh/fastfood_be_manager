package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository; // để map customerID sang entity

    // 🟢 Lấy tất cả đơn hàng
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🟢 Thêm đơn hàng mới
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);

        // Lấy thông tin customer từ ID
        if (orderDTO.getCustomerID() != null) {
            Optional<Customer> customerOpt = customerRepository.findById(orderDTO.getCustomerID());
            customerOpt.ifPresent(order::setCustomer);
        }

        // Lưu đơn hàng
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    // 🟡 Cập nhật đơn hàng
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();

            if (orderDTO.getCustomerID() != null) {
                Optional<Customer> customerOpt = customerRepository.findById(orderDTO.getCustomerID());
                customerOpt.ifPresent(order::setCustomer);
            }

            order.setOrderDate(orderDTO.getOrderDate());
            order.setTotalAmount(orderDTO.getTotalAmount());
            order.setStatus(orderDTO.getStatus());

            Order updatedOrder = orderRepository.save(order);
            return OrderMapper.toDTO(updatedOrder);
        } else {
            throw new RuntimeException("Không tìm thấy đơn hàng với ID: " + id);
        }
    }

    // 🔵 Phân trang đơn hàng
    public Page<OrderDTO> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 vì Spring Data bắt đầu từ index 0
        Page<Order> ordersPage = orderRepository.findAll(pageable);

        return ordersPage.map(order -> new OrderDTO(
                order.getOrderID(),
                order.getCustomer().getCustomerID(), // Lấy customerID
                order.getStaff().getStaffID(),       // Lấy staffID
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus()
        ));
    } // ✅ Đóng ngoặc bị thiếu

    // 🟣 Lấy tất cả đơn hàng có sắp xếp
    public List<OrderDTO> getAllOrders(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return orderRepository.findAll(sort)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔴 Xóa đơn hàng
    @Transactional
    public void deleteOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getPromotions().clear(); // Xoá liên kết với promotions (nếu có)
            orderRepository.delete(order); // Xoá order
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + id);
        }
    }
    public List<Order> searchOrders(Integer customerID, LocalDateTime startDate, LocalDateTime endDate, String status) {
        return orderRepository.findByCustomer_CustomerIDAndOrderDateBetweenAndStatus(customerID, startDate, endDate, status);
    }
}
