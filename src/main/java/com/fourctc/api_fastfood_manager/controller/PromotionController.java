package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.PromotionDTO;
import com.fourctc.api_fastfood_manager.entity.Promotion;
import com.fourctc.api_fastfood_manager.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotions")
@CrossOrigin(origins = "*")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    // 🧾 Lấy danh sách có phân trang, sắp xếp
    @GetMapping
    public ResponseEntity<Page<PromotionDTO>> getAllPromotions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "promotionID") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(promotionService.getAllPromotions(page, size, sortBy, direction));
    }

    // 🔍 Tìm kiếm có phân trang
    @GetMapping("/search")
    public ResponseEntity<Page<PromotionDTO>> searchPromotions(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(promotionService.searchPromotions(keyword, page, size));
    }

    // ➕ Thêm khuyến mãi
    @PostMapping
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        try {
            return ResponseEntity.ok(promotionService.addPromotion(promotion));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✏️ Sửa khuyến mãi
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion) {
        try {
            PromotionDTO updated = promotionService.updatePromotion(id, promotion);
            if (updated == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ❌ Xóa khuyến mãi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Integer id) {
        boolean deleted = promotionService.deletePromotion(id);
        if (!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Đã xóa khuyến mãi thành công!");
    }
}