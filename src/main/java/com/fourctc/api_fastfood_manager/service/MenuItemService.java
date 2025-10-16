package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import com.fourctc.api_fastfood_manager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
