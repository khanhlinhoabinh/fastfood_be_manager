package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.dto.PaymentDTO;
import com.fourctc.api_fastfood_manager.entity.Payment;
import com.fourctc.api_fastfood_manager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/api/payments") // ✅ URL gốc cho module Payment
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * ✅ API 1: Lấy danh sách thanh toán (KHÔNG SORT)
     * URL: GET http://localhost:8080/api/payments
     * Trả về: Danh sách Payment (ID, Order, method, amount, changeAmount, paymentDate)
     * <p>
     * Ví dụ (Postman):
     * Method: GET
     * URL   : http://localhost:8080/api/payments
     */
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    /**
     * ✅ API 2: Lấy danh sách thanh toán CÓ SẮP XẾP
     * URL:
     * GET http://localhost:8080/api/payments/sort?sortBy=paymentDate&order=asc
     * GET http://localhost:8080/api/payments/sort?sortBy=amount&order=desc
     * Query:
     * - sortBy: "paymentDate" | "amount" (mặc định: paymentDate)
     * - order : "asc" | "desc" (mặc định: asc)
     * <p>
     * Ví dụ (Postman):
     * Method: GET
     * URL   : http://localhost:8080/api/payments/sort?sortBy=amount&order=desc
     */
    @GetMapping("/sort")
    public List<Payment> getSortedPayments(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order
    ) {
        return paymentService.getSortedPayments(sortBy, order);
    }

    /**
     * ✅ API 3: Tìm kiếm thanh toán
     * URL:
     * GET http://localhost:8080/api/payments/search?orderId=123&method=CASH
     * GET http://localhost:8080/api/payments/search?orderId=123
     * GET http://localhost:8080/api/payments/search?method=MOMO
     * Query (optional):
     * - orderId: ID của đơn hàng liên kết
     * - method : Phương thức (contains, ignore case)
     * <p>
     * Ví dụ (Postman):
     * Method: GET
     * URL   : http://localhost:8080/api/payments/search?orderId=5&method=cash
     */
    @GetMapping("/search")
    public List<Payment> searchPayments(
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) String method
    ) {
        return paymentService.searchPayments(orderId, method);
    }

    /**
     * ✅ API 4: Thêm thanh toán mới (dùng DTO)
     * URL:
     * POST http://localhost:8080/api/payments
     * Body (JSON):
     * {
     * "orderId": 5,
     * "method": "CASH",
     * "amount": 150000,
     * "changeAmount": 5000,
     * "paymentDate": "2025-10-21T10:30:00" // optional, nếu null backend sẽ set "now"
     * }
     * <p>
     * Ví dụ (Postman):
     * Method: POST
     * URL   : http://localhost:8080/api/payments
     */
    @PostMapping
    public Payment createPayment(@RequestBody PaymentDTO dto) {
        return paymentService.createPayment(dto);
    }

    /**
     * ✅ API 5: Chỉnh sửa thanh toán (dùng DTO, có thể đổi orderId)
     * URL:
     * PUT http://localhost:8080/api/payments/{id}
     * Path:
     * - id: ID payment cần cập nhật
     * Body (JSON): có thể gửi một phần field, phần không gửi sẽ giữ nguyên
     * Ví dụ (Postman):
     * Method: PUT
     * URL   : http://localhost:8080/api/payments/7
     * Body  :
     * {
     * "orderId": 5,
     * "method": "MOMO",
     * "amount": 200000,
     * "changeAmount": 0,
     * "paymentDate": "2025-10-21T12:00:00"
     * }
     */

    
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Integer id, @RequestBody PaymentDTO dto) {
        return paymentService.updatePayment(id, dto);
    }

    /**
     * ✅ API 6: Xoá thanh toán
     * URL:
     * DELETE http://localhost:8080/api/payments/{id}
     * <p>
     * Ví dụ (Postman):
     * Method: DELETE
     * URL   : http://localhost:8080/api/payments/7
     */
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
    }

    /**
     * ✅ API 7: Phân trang thanh toán
     * URL: GET http://localhost:8080/api/payments/page?page=0&size=10
     */
    @GetMapping("/page")
    public Page<Payment> getPaymentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return paymentService.getPaymentsPage(page, size);
    }

    /**
     * ✅ API 4: Tìm kiếm thanh toán
     */
}