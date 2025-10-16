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
}
