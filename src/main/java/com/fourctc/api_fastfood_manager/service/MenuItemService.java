package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import com.fourctc.api_fastfood_manager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * ✅ Lấy danh sách tất cả món ăn từ DB.
     *
     * @return danh sách MenuItem
     */
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    /**
     * ✅ Hàm sắp xếp danh sách món ăn theo field (name, price, ...)
     * @param field - tên trường để sắp xếp
     * @param order - hướng sắp xếp (asc hoặc desc)
     * @return danh sách món ăn đã sắp xếp
     */
    public List<MenuItem> getSortedMenuItems(String field, String order) {
        Sort sort = Sort.by(field);
        if ("desc".equalsIgnoreCase(order)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return menuItemRepository.findAll(sort);
    }

    /**
     * ✅ Thêm món ăn mới
     */
    public MenuItem createMenuItem(MenuItem item) {
        validateMenuItem(item);
        return menuItemRepository.save(item);
    }

    /**
     * ✅ Chỉnh sửa món ăn
     */
    public MenuItem updateMenuItem(Integer id, MenuItem updatedItem) {
        MenuItem existingItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy món ăn với ID: " + id));

        validateMenuItem(updatedItem);

        existingItem.setName(updatedItem.getName());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setStockQuantity(updatedItem.getStockQuantity());
        existingItem.setPrepTime(updatedItem.getPrepTime());
        existingItem.setImage(updatedItem.getImage());
        existingItem.setPromotions(updatedItem.getPromotions());

        return menuItemRepository.save(existingItem);
    }

    /**
     * ✅ Xóa món ăn theo ID
     *
     * @param id - ID món ăn cần xóa
     * @return
     * @throws RuntimeException nếu không tìm thấy món ăn
     */
    public boolean deleteMenuItem(Integer id) {
        if (!menuItemRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy món ăn với ID: " + id);
        }
        menuItemRepository.deleteById(id);
        return false;
    }

    /**
     * ✅ Tìm kiếm món ăn theo tên hoặc loại (category)
     *
     * @param keyword - từ khóa tìm kiếm (có thể là một phần của tên hoặc loại)
     * @return danh sách món ăn khớp với từ khóa
     *
     * Ví dụ:
     *   🔹 keyword = "ga"  → tìm món có “ga” trong tên hoặc category
     *   🔹 keyword = "nuoc" → tìm món có “nuoc” trong category
     */
    public List<MenuItem> searchMenuItems(String keyword) {
        return menuItemRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }

    /**
     * ✅ Kiểm tra dữ liệu hợp lệ trước khi thêm/sửa
     */
    private void validateMenuItem(MenuItem item) {
        if (item.getName() == null || item.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên món ăn không được để trống");
        }
        if (item.getPrice() == null || item.getPrice() < 0) {
            throw new IllegalArgumentException("Giá món ăn phải ≥ 0");
        }
        if (item.getStockQuantity() == null || item.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Tồn kho phải ≥ 0");
        }
    }
}
