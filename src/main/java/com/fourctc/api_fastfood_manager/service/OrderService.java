package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fourctc.api_fastfood_manager.dto.OrderDTO;
import com.fourctc.api_fastfood_manager.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // OrderService.java
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return orderRepository.findAll(sort)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
