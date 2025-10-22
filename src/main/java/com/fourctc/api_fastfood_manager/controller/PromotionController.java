package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Promotion;
import com.fourctc.api_fastfood_manager.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@CrossOrigin(origins = "*")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    // 🟢 API: Lấy danh sách khuyến mãi (có hỗ trợ sắp xếp)
    @GetMapping
    public List<Promotion> getAllPromotions(@RequestParam(required = false) String sortBy) {
        return promotionService.getAllPromotions(sortBy);

    }
    // 🟡 API: Thêm khuyến mãi mới
    @PostMapping
    public Promotion addPromotion(@RequestBody Promotion promotion) {
        return promotionService.addPromotion(promotion);
    }
    // 🟠 API: Cập nhật khuyến mãi theo ID
    @PutMapping("/{id}")
    public Promotion updatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion) {
        return promotionService.updatePromotion(id, promotion);
    }
    // 🔴 API: Xóa khuyến mãi theo ID
    @DeleteMapping("/{id}")
    public String deletePromotion(@PathVariable Integer id) {
        promotionService.deletePromotion(id);
        return "🗑️ Đã xóa khuyến mãi có ID: " + id;
    }
    // 🔍 API: Tìm kiếm khuyến mãi theo tên hoặc loại
    @GetMapping("/search")
    public List<Promotion> searchPromotions(@RequestParam(required = false) String keyword) {
        return promotionService.searchPromotions(keyword);
    }

}
