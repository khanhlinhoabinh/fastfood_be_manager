package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // ✅ 1️⃣ Lấy danh sách tất cả đơn hàng (không phân trang)
    List<Order> findAll();

    // ✅ 2️⃣ Phân trang
    Page<Order> findAll(Pageable pageable);

    // ✅ 3️⃣ Tìm theo ID khách hàng
    List<Order> findByCustomer_CustomerID(Integer customerID);

    // ✅ 4️⃣ Tìm theo khoảng thời gian
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // ✅ 5️⃣ Tìm theo trạng thái
    List<Order> findByStatus(String status);

    // ✅ 6️⃣ Kết hợp tìm kiếm nâng cao
    List<Order> findByCustomer_CustomerIDAndOrderDateBetweenAndStatus(
            Integer customerID,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String status
    );

    // ✅ 7️⃣ Tìm kiếm theo từ khóa (phục vụ FE search)
    //   - Cho phép tìm theo mã KH, trạng thái, mã NV
    //   - Tự động hoạt động với phân trang + sort
    @Query("""
        SELECT o FROM Order o
        WHERE 
            CAST(o.customer.customerID AS string) LIKE CONCAT('%', :keyword, '%')
            OR LOWER(o.status) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR CAST(o.staff.staffID AS string) LIKE CONCAT('%', :keyword, '%')
    """)
    Page<Order> searchOrders(@Param("keyword") String keyword, Pageable pageable);
}
