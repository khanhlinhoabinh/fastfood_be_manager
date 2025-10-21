package com.fourctc.api_fastfood_manager.repository;

import com.fourctc.api_fastfood_manager.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // Tìm theo phương thức (contains, ignore case)
    List<Payment> findByMethodContainingIgnoreCase(String method);

    // Tìm theo orderId (ID của đơn hàng liên kết)
    // ⚠️ Nếu Order entity của bạn dùng tên ID khác (id / orderId),
    // hãy đổi "OrderID" trong method này cho khớp: findByOrder_Id(...) hoặc findByOrder_OrderId(...)
    List<Payment> findByOrder_OrderID(Integer orderId);

    // Tìm theo cả orderId + method
    List<Payment> findByOrder_OrderIDAndMethodContainingIgnoreCase(Integer orderId, String method);
}