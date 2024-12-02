package com.example.school_management_system.service;

import com.example.school_management_system.dto.ControlDTO;
import com.example.school_management_system.dto.request.CreateControlDTO;

import java.util.List;

public interface ControlService {
    ControlDTO createControl(CreateControlDTO createControlDTO);
    ControlDTO getControlById(Long id);
    List<ControlDTO> getAllControls();
    void deleteControl(Long id);
    List<ControlDTO> getGradesByStudentId(Long studentId);
    List<ControlDTO> getGradesByExamId(Long examId);
}
