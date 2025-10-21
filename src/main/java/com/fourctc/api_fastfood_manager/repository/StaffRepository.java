package com.fourctc.api_fastfood_manager.repository;


import com.fourctc.api_fastfood_manager.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByNameContainingIgnoreCaseOrPositionContainingIgnoreCase(String name, String position);
}