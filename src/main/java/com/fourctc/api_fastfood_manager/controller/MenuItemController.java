package com.fourctc.api_fastfood_manager.controller;
import com.fourctc.api_fastfood_manager.entity.MenuItem;
import com.fourctc.api_fastfood_manager.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/menuitems")
@CrossOrigin(origins = "*") // Cho phép truy cập từ FE hoặc Postman
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    /**
     * API: Lấy danh sách tất cả món ăn.
     *
     * GET http://localhost:8080/api/menuitems
     */
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    // 🆕 Sắp xếp danh sách món ăn theo tên hoặc giá
    // http://localhost:8080/api/menuitems/sort
    // Giảm GET http://localhost:8080/api/menuitems/sort?field=price&order=desc
    // Tăng GET http://localhost:8080/api/menuitems/sort?field=name&order=asc
    @GetMapping("/sort")
    public List<MenuItem> getSortedMenuItems(
            @RequestParam(defaultValue = "name") String field,
            @RequestParam(defaultValue = "asc") String order) {
        return menuItemService.getSortedMenuItems(field, order);
    }

    /**
     * API: Thêm món ăn mới.
     * POST http://localhost:8080/api/menuitems
     *
     *
     * Body: JSON chứa thông tin món ăn
     */
    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

    /**
     * API: Chỉnh sửa thông tin món ăn.
     * PUT http://localhost:8080/api/menuitems/{id}
     * Body: JSON chứa thông tin món ăn cập nhật
     */
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Integer id, @RequestBody MenuItem updatedItem) {
        return menuItemService.updateMenuItem(id, updatedItem);
    }
    /**
     * 🗑️ API: Xóa món ăn theo ID
     * DELETE http://localhost:8080/api/menuitems/{id}
     */
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Integer id) {
        boolean deleted = menuItemService.deleteMenuItem(id);
        return deleted ? "Xóa món ăn thành công!" : "Không tìm thấy món ăn để xóa.";
    }

    /**
     * 🔍 API: Tìm kiếm món ăn theo tên (hoặc một phần tên)
     * GET http://localhost:8080/api/menuitems/search?keyword=ga
     */
    @GetMapping("/search")
    public List<MenuItem> searchMenuItems(@RequestParam String keyword) {
        return menuItemService.searchMenuItems(keyword);
    }

}
