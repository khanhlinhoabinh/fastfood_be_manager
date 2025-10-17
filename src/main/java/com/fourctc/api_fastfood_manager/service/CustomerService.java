package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * ✅ Hàm 1: Lấy danh sách tất cả khách hàng (không sort)
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * ✅ Hàm 2: Lấy danh sách khách hàng có sắp xếp
     * @param sortBy - trường muốn sắp xếp (name hoặc loyaltyPoints)
     * @param order - hướng sắp xếp (asc hoặc desc)
     */
    public List<Customer> getSortedCustomers(String sortBy, String order) {
        // Mặc định sắp xếp theo tên
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "name";
        }

        // Mặc định tăng dần
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;

        return customerRepository.findAll(Sort.by(direction, sortBy));
    }

    /**
     * ✅ Hàm 3: Tìm kiếm khách hàng theo tên, số điện thoại hoặc email
     * @param keyword - từ khóa tìm kiếm (có thể là 1 phần của name, phone hoặc email)
     * @return danh sách khách hàng khớp
     *
     * Ví dụ:
     *  🔹 keyword = "nguyen" → tìm tất cả khách hàng có "nguyen" trong tên hoặc email
     *  🔹 keyword = "090" → tìm tất cả khách hàng có "090" trong số điện thoại
     */
    public List<Customer> searchCustomers(String keyword) {
        return customerRepository.findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, keyword
        );
    }

    /**
     * ✅ Hàm 4: Xóa khách hàng theo ID
     * @param id - ID của khách hàng cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy khách hàng
     */
    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
