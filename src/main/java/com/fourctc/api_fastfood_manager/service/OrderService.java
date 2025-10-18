package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository; // để map customerID sang entity

    //Lấy danh sách đơn hàng
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🟢 Feature 5: Thêm đơn hàng mới
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);

        // Lấy thông tin customer từ ID
        if (orderDTO.getCustomerID() != null) {
            Optional<Customer> customerOpt = customerRepository.findById(orderDTO.getCustomerID());
            customerOpt.ifPresent(order::setCustomer);
        }
        /*
        // Lấy thông tin staff từ ID
        if (orderDTO.getStaffID() != null) {
            Optional<Staff> staffOpt = staffRepository.findById(orderDTO.getStaffID());  // Bạn cần chắc chắn rằng staffRepository tồn tại và có thể tìm kiếm nhân viên
            staffOpt.ifPresent(order::setStaff);  // Thiết lập thông tin staff vào order
        }
        */
        // Lưu đơn hàng
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    // 🟡 Feature 7: Chỉnh sửa thông tin đơn hàng
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

    // Phân trang
    public Page<OrderDTO> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 vì Spring Data bắt đầu từ index 0
        Page<Order> ordersPage = orderRepository.findAll(pageable);

        return ordersPage.map(order -> new OrderDTO(
                order.getOrderID(),
                order.getCustomer().getCustomerID(), // Lấy thông tin customerID từ Customer entity
                order.getStaff().getStaffID(), // Lấy thông tin staffID từ Staff entity
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus()
        ));
    }
    // Xap xep don hang
    public List<OrderDTO> getAllOrders (String sortBy, String direction){
        // Kiểm tra direction, nếu không phải "asc" hoặc "desc" mặc định chọn "asc"
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, sortBy);
        return orderRepository.findAll(sort)
            .stream()
            .map(OrderMapper::toDTO)
            .collect(Collectors.toList());
    }

     // Xoa don hang
    @Transactional
    public void deleteOrderById (Integer Id){
        Order order = orderRepository.findById(Id)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + Id));

        order.getPromotions().clear(); // Xoá liên kết với promotions
        orderRepository.delete(order); // Xoá order và các liên kết cascade
    }
}
