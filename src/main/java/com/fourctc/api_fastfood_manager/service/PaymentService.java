package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.dto.PaymentDTO;
import com.fourctc.api_fastfood_manager.entity.Order;
import com.fourctc.api_fastfood_manager.entity.Payment;
import com.fourctc.api_fastfood_manager.repository.OrderRepository;
import com.fourctc.api_fastfood_manager.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * ✅ Hàm 1: Lấy danh sách thanh toán (KHÔNG SORT)
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * ✅ Hàm 2: Lấy danh sách thanh toán CÓ SẮP XẾP theo paymentDate hoặc amount
     *
     * @param sortBy : "paymentDate" hoặc "amount" (default: paymentDate)
     * @param order  : "asc" hoặc "desc" (default: asc)
     */
    public List<Payment> getSortedPayments(String sortBy, String order) {
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "paymentDate";
        }
        if (!sortBy.equals("paymentDate") && !sortBy.equals("amount")) {
            throw new IllegalArgumentException("sortBy chỉ được 'paymentDate' hoặc 'amount'");
        }
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return paymentRepository.findAll(Sort.by(direction, sortBy));
    }
    /**
     * ✅ Phân trang danh sách thanh toán
     * Ví dụ: GET /api/payments/page?page=0&size=10
     */
    public Page<Payment> getPaymentsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("paymentDate").descending());
        return paymentRepository.findAll(pageable);
    }

    /**
     * ✅ Tìm kiếm thanh toán
     * - Theo Mã đơn: dùng orderId (ID của Order)
     * - Theo Phương thức: method (contains, ignore case)
     * - Cả hai tham số đều optional:
     * - nếu cả hai null/rỗng -> trả tất cả
     */
    public List<Payment> searchPayments(Integer orderId, String method) {
        boolean hasOrderId = orderId != null;
        boolean hasMethod = method != null && !method.isBlank();

        if (hasOrderId && hasMethod) {
            return paymentRepository.findByOrder_OrderIDAndMethodContainingIgnoreCase(orderId, method.trim());
        } else if (hasOrderId) {
            return paymentRepository.findByOrder_OrderID(orderId);
        } else if (hasMethod) {
            return paymentRepository.findByMethodContainingIgnoreCase(method.trim());
        } else {
            return paymentRepository.findAll();
        }
    }

    /**
     * ✅ Thêm thanh toán mới (nhận DTO)
     */
    public Payment createPayment(PaymentDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Order với ID: " + dto.getOrderId()));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(dto.getMethod());
        payment.setAmount(dto.getAmount());
        payment.setChangeAmount(dto.getChangeAmount());
        payment.setPaymentDate(dto.getPaymentDate() != null ? dto.getPaymentDate() : LocalDateTime.now());

        validatePayment(payment, true);
        return paymentRepository.save(payment);
    }

    /**
     * ✅ Chỉnh sửa thanh toán (nhận DTO)
     */
    public Payment updatePayment(Integer id, PaymentDTO dto) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Payment với ID: " + id));

        // Đổi Order nếu truyền orderId mới
        if (dto.getOrderId() != null) {
            Order order = orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Order với ID: " + dto.getOrderId()));
            existing.setOrder(order);
        }
        // Cập nhật các field còn lại nếu có trong DTO
        if (dto.getMethod() != null) existing.setMethod(dto.getMethod());
        if (dto.getAmount() != null) existing.setAmount(dto.getAmount());
        if (dto.getChangeAmount() != null) existing.setChangeAmount(dto.getChangeAmount());
        if (dto.getPaymentDate() != null) existing.setPaymentDate(dto.getPaymentDate());

        validatePayment(existing, false);
        return paymentRepository.save(existing);
    }

    /**
     * ✅ Xóa thanh toán
     */
    public void deletePayment(Integer id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Không tồn tại Payment với ID: " + id);
        }
        paymentRepository.deleteById(id);
    }

    /**
     * Validate dữ liệu Payment (Double)
     */
    private void validatePayment(Payment p, boolean isCreate) {
        if (p.getOrder() == null)
            throw new IllegalArgumentException("Order không được để trống (phải có orderId liên kết)");
        if (p.getMethod() == null || p.getMethod().trim().isEmpty())
            throw new IllegalArgumentException("Phương thức (method) không được để trống");
        if (p.getAmount() == null || p.getAmount() < 0)
            throw new IllegalArgumentException("Số tiền (amount) phải ≥ 0");
        if (p.getChangeAmount() == null || p.getChangeAmount() < 0)
            throw new IllegalArgumentException("Tiền thừa (changeAmount) phải ≥ 0");
        if (!isCreate && p.getPaymentDate() == null)
            throw new IllegalArgumentException("Ngày thanh toán (paymentDate) không được null khi cập nhật");
    }
}