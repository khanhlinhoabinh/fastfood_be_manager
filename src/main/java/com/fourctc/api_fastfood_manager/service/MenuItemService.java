package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import com.fourctc.api_fastfood_manager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.*;

import java.util.List;
@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    /**
     * Lấy danh sách tất cả món ăn từ DB.
     *
     * @return danh sách MenuItem
     */
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // hàm sắp xếp
    public List<MenuItem> getSortedMenuItems(String field, String order) {
        Sort sort = Sort.by(field);
        if ("desc".equalsIgnoreCase(order)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return menuItemRepository.findAll(sort);
    }

    // Thêm món ăn mới
    public MenuItem createMenuItem(MenuItem item) {
        validateMenuItem(item);
        return menuItemRepository.save(item);
    }

    // Chỉnh sửa món ăn
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

    // Kiểm tra dữ liệu hợp lệ
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
    /**
     * Xóa món ăn theo ID.
     *
     * @param id ID của món ăn cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy món ăn.
     */
    public boolean deleteMenuItem(Integer id) {
        // 1. Kiểm tra sự tồn tại
        if (menuItemRepository.existsById(id)) {
            // 2. Nếu tồn tại, thực hiện xóa
            menuItemRepository.deleteById(id);
            return true;
        }
        // 3. Nếu không tìm thấy
        return false;
    }

    /**
     * Tìm kiếm món ăn theo tên (hoặc một phần tên).
     *
     * @param keyword Từ khóa tìm kiếm (tên món ăn).
     * @return Danh sách MenuItem khớp với từ khóa.
     */
    public List<MenuItem> searchMenuItems(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            // Nếu từ khóa rỗng, trả về tất cả hoặc danh sách rỗng (tùy theo logic nghiệp vụ)
            // Ở đây, tôi chọn trả về tất cả nếu keyword rỗng
            return menuItemRepository.findAll();
        }
        // Sử dụng phương thức custom từ Repository: findByNameContainingIgnoreCase
        return menuItemRepository.findByNameContainingIgnoreCase(keyword);
    }

    // ✅ Phân trang món ăn
    public Page<MenuItem> getMenuItemsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return menuItemRepository.findAll(pageable);
    }
}
