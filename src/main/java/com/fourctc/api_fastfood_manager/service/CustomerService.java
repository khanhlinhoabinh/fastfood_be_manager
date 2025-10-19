package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Customer;
import com.fourctc.api_fastfood_manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.regex.Pattern;
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
     *
     * @param sortBy - trường muốn sắp xếp (name hoặc loyaltyPoints)
     * @param order  - hướng sắp xếp (asc hoặc desc)
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

    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));

        validateCustomer(updatedCustomer);

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setLoyaltyPoints(updatedCustomer.getLoyaltyPoints());
        existingCustomer.setMemberType(updatedCustomer.getMemberType());

        return customerRepository.save(existingCustomer);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống");
        }

        if (customer.getPhone() == null || !Pattern.matches("^0\\d{9}$", customer.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ (phải có 10 chữ số và bắt đầu bằng 0)");
        }

        if (customer.getEmail() == null || !Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", customer.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
    }

    /**
     * ✅ Hàm 3: Tìm kiếm khách hàng theo tên, số điện thoại hoặc email
     *
     * @param - từ khóa tìm kiếm (có thể là 1 phần của name, phone hoặc email)
     * @return danh sách khách hàng khớp
     * <p>
     * Ví dụ:
     * 🔹 keyword = "nguyen" → tìm tất cả khách hàng có "nguyen" trong tên hoặc email
     * 🔹 keyword = "090" → tìm tất cả khách hàng có "090" trong số điện thoại
     */
    public List<Customer> searchCustomers(String keyword) {
        return customerRepository.findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, keyword
        );
    }

    /**
     * ✅ Hàm 4: Xóa khách hàng theo ID
     *
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
    public Page<Customer> getCustomersPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return customerRepository.findAll(pageable);
    }

}
