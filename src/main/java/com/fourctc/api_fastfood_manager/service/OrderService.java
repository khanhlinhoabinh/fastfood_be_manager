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
import java.time.LocalDateTime;


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

    public List<OrderDTO> searchOrders(Integer customerID, LocalDateTime startDate, LocalDateTime endDate, String status) {
        List<Order> orders;

        boolean hasCustomerID = customerID != null;
        boolean hasStartDate = startDate != null;
        boolean hasEndDate = endDate != null;
        boolean hasStatus = status != null && !status.isBlank();

        // Trường hợp đầy đủ: customerID + status + khoảng thời gian
        if (hasCustomerID && hasStartDate && hasEndDate && hasStatus) {
            orders = orderRepository.findByCustomerIDAndStatusAndOrderDateBetween(customerID, startDate, endDate, status);
        }
        // customerID + status
        else if (hasCustomerID && hasStatus) {
            orders = orderRepository.findByCustomerIDAndStatus(customerID, status);
        }
        // customerID + khoảng thời gian
        else if (hasCustomerID && hasStartDate && hasEndDate) {
            orders = orderRepository.findByCustomerIDAndOrderDateBetween(customerID, startDate, endDate);
        }
        // status + khoảng thời gian
        else if (hasStatus && hasStartDate && hasEndDate) {
            orders = orderRepository.findByStatusAndOrderDateBetween(status, startDate, endDate);
        }
        // chỉ customerID
        else if (hasCustomerID) {
            orders = orderRepository.findByCustomerID(customerID);
        }
        // chỉ status
        else if (hasStatus) {
            orders = orderRepository.findByStatus(status);
        }
        // chỉ khoảng thời gian
        else if (hasStartDate && hasEndDate) {
            orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        }
        // không có điều kiện nào
        else {
            orders = orderRepository.findAll();
        }

        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
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
