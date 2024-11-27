package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDTO {
    private Long classId;
    private String className;
    private Integer gradeLevel;
}
