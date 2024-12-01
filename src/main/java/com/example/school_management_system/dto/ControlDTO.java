package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ControlDTO {
    private Long controlId;
    private Long studentId;
    private Long examId;
    private Float grade;
}
