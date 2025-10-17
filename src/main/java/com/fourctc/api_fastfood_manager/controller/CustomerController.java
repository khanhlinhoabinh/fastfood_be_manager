package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // ✅ URL gốc cho module Customer
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * ✅ API 1: Lấy danh sách tất cả khách hàng
     * URL: GET http://localhost:8080/api/customers
     * Trả về: ID, name, phone, email, loyaltyPoints, memberType
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    /**
     * ✅ API 2: Lấy danh sách khách hàng có sắp xếp
     * URL: GET /api/customers/sort?sortBy=name&order=asc
     * - sortBy: name hoặc loyaltyPoints
     * - order: asc hoặc desc
     *
     * Ví dụ:
     *  🔹 /api/customers/sort?sortBy=name&order=asc
     *  🔹 /api/customers/sort?sortBy=loyaltyPoints&order=desc
     */
    @GetMapping("/sort")
    public List<Customer> getSortedCustomers(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {
        return customerService.getSortedCustomers(sortBy, order);
        
    }
}
