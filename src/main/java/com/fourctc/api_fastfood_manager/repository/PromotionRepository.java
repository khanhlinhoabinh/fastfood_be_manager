package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    // Tạm thời chỉ cần hàm mặc định findAll() của JpaRepository

    List<Promotion> findByNameContainingIgnoreCaseOrTypeContainingIgnoreCase(String name, String type);
}

