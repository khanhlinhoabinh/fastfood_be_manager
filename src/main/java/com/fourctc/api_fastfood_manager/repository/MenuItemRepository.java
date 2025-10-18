package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    // JpaRepository đã có sẵn các hàm CRUD: findAll(), findById(), save(), deleteById()

    // 1. Phương thức Sắp xếp (Dùng cho API GET /sort)
    // Phương thức này đã được bạn định nghĩa, dùng để hỗ trợ việc sắp xếp.
    // JpaRepository.findAll(Sort sort) đã tự động hỗ trợ điều này, nhưng việc định nghĩa lại
    // ở đây giúp làm rõ mục đích sử dụng.
    @Override
    List<MenuItem> findAll(Sort sort);

    // 2. Phương thức Tìm kiếm (Dùng cho API GET /search)
    /**
     * Tìm kiếm các món ăn có tên chứa từ khóa (case-insensitive - không phân biệt chữ hoa/thường).
     * Spring Data JPA sẽ tự động tạo truy vấn LIKE cho tên.
     * Tương đương với: SELECT * FROM menu_item WHERE name LIKE '%keyword%'
     */
    List<MenuItem> findByNameContainingIgnoreCase(String keyword);
}