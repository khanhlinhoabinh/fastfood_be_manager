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
        return List.of();
    }
}
