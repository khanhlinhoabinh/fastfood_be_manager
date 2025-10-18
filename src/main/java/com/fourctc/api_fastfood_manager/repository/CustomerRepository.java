package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // ✅ Không cần viết thêm query thủ công vì JpaRepository hỗ trợ sort tự động

    /**
     * ✅ Hàm tìm kiếm khách hàng theo tên, số điện thoại hoặc email
     * - Sử dụng cú pháp “ContainingIgnoreCase” để tìm kiếm một phần (LIKE %keyword%)
     * - Không phân biệt chữ hoa / thường
     *
     * Ví dụ:
     *   🔹 keyword = "nguyen"  → tìm tất cả khách hàng có "nguyen" trong tên hoặc email
     *   🔹 keyword = "090"     → tìm tất cả khách hàng có "090" trong số điện thoại
     */
    List<Customer> findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String phone, String email
    );
}
