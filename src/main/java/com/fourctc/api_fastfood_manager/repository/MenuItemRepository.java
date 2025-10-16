package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    // JpaRepository đã có sẵn các hàm CRUD: findAll(), findById(), save(), deleteById()

}
