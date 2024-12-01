package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDTO {
    private Long subjectId;       // Primary Key
    private String subjectName;   // Name of the subject
    private Long classId;         // Foreign Key to Class
}

