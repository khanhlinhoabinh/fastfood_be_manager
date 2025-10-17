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


}
