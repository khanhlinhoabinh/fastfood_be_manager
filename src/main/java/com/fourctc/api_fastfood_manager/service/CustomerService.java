package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * ✅ Hàm 1: Lấy danh sách tất cả khách hàng (không sort)
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    
}
