package com.fourctc.api_fastfood_manager.service;

import com.fourctc.api_fastfood_manager.entity.Staff;
import com.fourctc.api_fastfood_manager.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public List<Staff> getSortedStaff(String sortBy, String order) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "name";
        }
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return staffRepository.findAll(Sort.by(direction, sortBy));
    }

    public Staff createStaff(Staff staff) {
        validateStaff(staff);
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Integer id, Staff updatedStaff) {
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));

        validateStaff(updatedStaff);

        existingStaff.setName(updatedStaff.getName());
        existingStaff.setPosition(updatedStaff.getPosition());
        existingStaff.setShift(updatedStaff.getShift());
        existingStaff.setSalary(updatedStaff.getSalary());

        return staffRepository.save(existingStaff);
    }

    public boolean deleteStaff(Integer id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Staff> searchStaff(String keyword) {
        return staffRepository.findByNameContainingIgnoreCaseOrPositionContainingIgnoreCase(keyword, keyword);
    }

    public Page<Staff> getStaffPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return staffRepository.findAll(pageable);
    }

    private void validateStaff(Staff staff) {
        if (staff.getName() == null || staff.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được để trống");
        }

        if (staff.getShift() == null || staff.getShift().trim().isEmpty()) {
            throw new IllegalArgumentException("Ca làm việc không hợp lệ");
        }

        if (staff.getSalary() == null || staff.getSalary() < 0) {
            throw new IllegalArgumentException("Lương phải lớn hơn hoặc bằng 0");
        }
    }
}