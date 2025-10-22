package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Promotion;
import com.fourctc.api_fastfood_manager.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    // 🟢 Hàm lấy danh sách khuyến mãi (có sắp xếp)
    public List<Promotion> getAllPromotions(String sortBy) {
        // Nếu không truyền sortBy thì mặc định sắp xếp theo tên
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "name";
        }
// Trả về danh sách khuyến mãi đã sắp xếp theo cột chỉ định
        return promotionRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }
    // 🟡 Hàm thêm khuyến mãi mới (Feature 3)
    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }
    // 🟠 Cập nhật thông tin khuyến mãi
    public Promotion updatePromotion(Integer id, Promotion updatedPromotion) {
        return promotionRepository.findById(id)
                .map(promotion -> {
                    promotion.setName(updatedPromotion.getName());
                    promotion.setType(updatedPromotion.getType());
                    promotion.setDiscountPercent(updatedPromotion.getDiscountPercent());
                    promotion.setExpiryDate(updatedPromotion.getExpiryDate());
                    return promotionRepository.save(promotion);
                })
                .orElseThrow(() -> new RuntimeException("❌ Không tìm thấy khuyến mãi có ID: " + id));
    }
}
