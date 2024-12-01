package com.example.school_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherDTO {
    private Long teacherId;      // Primary Key
    private Long userId;         // Foreign Key to User
    private Long subjectId;      // Foreign Key to Subject
}
