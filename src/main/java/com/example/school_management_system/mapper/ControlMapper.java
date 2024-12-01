package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.ControlDTO;
import com.example.school_management_system.entity.Control;

public class ControlMapper {

    public static ControlDTO toDTO(Control control) {
        ControlDTO dto = new ControlDTO();
        dto.setControlId(control.getControlId());
        dto.setStudentId(control.getStudent().getStudentId());
        dto.setExamId(control.getExam().getExamId());
        dto.setGrade(control.getGrade());
        return dto;
    }
}
