package com.fourctc.api_fastfood_manager.mapper;

import com.fourctc.api_fastfood_manager.dto.StaffDTO;
import com.fourctc.api_fastfood_manager.entity.Staff;

public class StaffMapper {

    public static StaffDTO toDTO(Staff staff) {
        return new StaffDTO(
                staff.getStaffID(),
                staff.getName(),
                staff.getPosition(),
                staff.getShift(),
                staff.getSalary()
        );
    }

    public static Staff toEntity(StaffDTO dto) {
        Staff staff = new Staff();
        staff.setStaffID(dto.getStaffID());
        staff.setName(dto.getName());
        staff.setPosition(dto.getPosition());
        staff.setShift(dto.getShift());
        staff.setSalary(dto.getSalary());
        return staff;
    }
}