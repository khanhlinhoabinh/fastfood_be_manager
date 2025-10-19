package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // ✅ URL gốc cho module Customer
@CrossOrigin(origins = "*")
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

    /**
     * ✅ API 3: Tìm kiếm khách hàng theo tên, số điện thoại hoặc email
     * URL: GET /api/customers/search?keyword=...
     * - keyword có thể là 1 phần tên, số điện thoại, hoặc email
     *
     * Ví dụ:
     *  🔹 /api/customers/search?keyword=nguyen
     *  🔹 /api/customers/search?keyword=090
     *  🔹 /api/customers/search?keyword=@gmail.com
     *
     * Trả về: Danh sách khách hàng khớp với từ khóa
     */
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String keyword) {
        return customerService.searchCustomers(keyword);
    }

    /**
     * ✅ API 4: Xóa khách hàng theo ID
     * URL: DELETE /api/customers/{id}
     *
     * Ví dụ:
     *  🔹 DELETE /api/customers/5
     *
     * Trả về:
     *  🔹 200 OK nếu xóa thành công
     *  🔹 404 Not Found nếu không tồn tại khách hàng
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.ok("✅ Xóa khách hàng ID " + id + " thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Không tìm thấy khách hàng có ID " + id);
        }
    }

    /**
     * ✅ API: Thêm khách hàng mới
     * POST http://localhost:8080/api/customers
     * Body: JSON chứa thông tin khách hàng
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * ✅ API: Chỉnh sửa thông tin khách hàng
     * PUT http://localhost:8080/api/customers/{id}
     *
     *
     * Body: JSON chứa thông tin cập nhật
     */
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }
    // ✅ API phân trang khách hàng
    // Ví dụ: GET http://localhost:8080/api/customers/page?page=0&size=10
    @GetMapping("/page")
    public Page<Customer> getCustomersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return customerService.getCustomersPage(page, size);
    }


}
