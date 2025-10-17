package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    // ✅ JpaRepository đã có sẵn các hàm CRUD: findAll(), findById(), save(), deleteById()
    List<MenuItem> findAll(Sort sort);

    /**
     * ✅ Tìm kiếm món ăn theo tên hoặc danh mục (category)
     * - Không phân biệt chữ hoa/thường (IgnoreCase)
     * - Hỗ trợ tìm một phần chuỗi (LIKE %keyword%)
     *
     * Ví dụ:
     *   🔹 keyword = "ga" → tìm tất cả món có "ga" trong tên hoặc category
     */
    List<MenuItem> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}
