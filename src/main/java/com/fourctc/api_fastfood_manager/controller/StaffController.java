package com.fourctc.api_fastfood_manager.controller;

import com.fourctc.api_fastfood_manager.entity.Staff;
import com.fourctc.api_fastfood_manager.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/sort")
    public List<Staff> getSortedStaff(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {
        return staffService.getSortedStaff(sortBy, order);
    }

    @GetMapping("/search")
    public List<Staff> searchStaff(@RequestParam String keyword) {
        return staffService.searchStaff(keyword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer id) {
        boolean deleted = staffService.deleteStaff(id);
        if (deleted) {
            return ResponseEntity.ok("✅ Xóa nhân viên ID " + id + " thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Không tìm thấy nhân viên có ID " + id);
        }
    }

    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Integer id, @RequestBody Staff updatedStaff) {
        return staffService.updateStaff(id, updatedStaff);
    }

    @GetMapping("/page")
    public Page<Staff> getStaffPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return staffService.getStaffPage(page, size);
    }
}