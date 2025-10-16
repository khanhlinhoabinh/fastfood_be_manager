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
}
