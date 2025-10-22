package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.PromotionDTO;
import com.fourctc.api_fastfood_manager.entity.Promotion;
import com.fourctc.api_fastfood_manager.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    // 🧾 Lấy danh sách có phân trang & sắp xếp
    public Page<PromotionDTO> getAllPromotions(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Promotion> promotions = promotionRepository.findAll(pageable);
        return promotions.map(PromotionDTO::new);
    }

    // 🔍 Tìm kiếm có phân trang
    public Page<PromotionDTO> searchPromotions(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Promotion> promotions = promotionRepository.searchPromotions(keyword, pageable);
        return promotions.map(PromotionDTO::new);
    }

    // ➕ Thêm khuyến mãi (có validate)
    public PromotionDTO addPromotion(Promotion promotion) {
        validatePromotion(promotion);
        Promotion saved = promotionRepository.save(promotion);
        return new PromotionDTO(saved);
    }

    // ✏️ Sửa khuyến mãi (có validate)
    public PromotionDTO updatePromotion(Integer id, Promotion promotionDetails) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        if (optionalPromotion.isPresent()) {
            Promotion existing = optionalPromotion.get();
            existing.setName(promotionDetails.getName());
            existing.setType(promotionDetails.getType());
            existing.setDiscountPercent(promotionDetails.getDiscountPercent());
            existing.setExpiryDate(promotionDetails.getExpiryDate());
            validatePromotion(existing);
            Promotion updated = promotionRepository.save(existing);
            return new PromotionDTO(updated);
        }
        return null;
    }

    // ❌ Xóa khuyến mãi
    public boolean deletePromotion(Integer id) {
        if (promotionRepository.existsById(id)) {
            promotionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🧠 Hàm kiểm tra dữ liệu hợp lệ
    private void validatePromotion(Promotion promotion) {
        if (promotion.getDiscountPercent() == null || promotion.getDiscountPercent() < 0 || promotion.getDiscountPercent() > 100) {
            throw new IllegalArgumentException("Giảm giá phải nằm trong khoảng từ 0 đến 100%");
        }
        if (promotion.getExpiryDate() == null || promotion.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày hết hạn phải lớn hơn hoặc bằng ngày hiện tại");
        }
    }
}