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
    /**
     * ✅ Hàm 2: Lấy danh sách khách hàng có sắp xếp
     * @param sortBy - trường muốn sắp xếp (name hoặc loyaltyPoints)
     * @param order - hướng sắp xếp (asc hoặc desc)
     */
    public List<Customer> getSortedCustomers(String sortBy, String order) {
        // Mặc định sắp xếp theo tên
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "name";
        }

        // Mặc định tăng dần
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return customerRepository.findAll(Sort.by(direction, sortBy));
    }
}
