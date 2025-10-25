package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import com.fourctc.api_fastfood_manager.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private StaffRepository staffRepository;

    // ✅ Lấy tất cả đơn hàng
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ Thêm mới đơn hàng
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);

        if (orderDTO.getCustomerID() != null) {
            customerRepository.findById(orderDTO.getCustomerID())
                    .ifPresent(order::setCustomer);
        }
        if (orderDTO.getStaffID() != null) {
            staffRepository.findById(orderDTO.getStaffID())
                    .ifPresent(order::setStaff);
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
        if (dto.getStaffID() != null) {
            staffRepository.findById(dto.getStaffID())
                    .ifPresent(order::setStaff);
        }

        order.setOrderDate(dto.getOrderDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());

        return OrderMapper.toDTO(orderRepository.save(order));
    }

    // ✅ Xóa đơn hàng
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng ID: " + id));

        if (order.getPromotions() != null) {
            order.getPromotions().clear();
        }
        orderRepository.delete(order);
    }

    // ✅ Sắp xếp
    public List<OrderDTO> getAllOrdersSorted(String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        return orderRepository.findAll(sort)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> searchOrdersSimple(Integer customerID, String status) {
        List<Order> orders;

        if (customerID != null && status != null && !status.isBlank()) {
            orders = orderRepository.findByCustomerIDAndStatus(customerID, status);
        } else if (customerID != null) {
            orders = orderRepository.findByCustomerID(customerID);
        } else if (status != null && !status.isBlank()) {
            orders = orderRepository.findByStatus(status);
        } else {
            orders = orderRepository.findAll();
        }

        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }
    public Page<OrderDTO> getOrdersPaged(int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> ordersPage = orderRepository.findAll(pageable);
        return ordersPage.map(OrderMapper::toDTO);
    }



}
