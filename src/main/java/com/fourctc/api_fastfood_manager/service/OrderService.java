package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository; // để map customerID sang entity

    // OrderService.java
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

}
